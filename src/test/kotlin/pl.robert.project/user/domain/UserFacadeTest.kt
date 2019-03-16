package pl.robert.project.user.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.function.Executable
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import pl.robert.project.user.domain.dto.*

@RunWith(SpringRunner::class)
@SpringBootTest
class UserFacadeTest {

    @Autowired
    private lateinit var facade: UserFacade

    @Test fun `Should save user and generate account confirmation token successfully`() {
        // Create user DTO
        val dto = CreateUserDTO()

        // Set required fields
        dto.login = "John Doe"
        dto.email = "helloimjohndoe@wp.pl"
        dto.phoneNumber = "509125569"
        dto.password = "hardPass1"
        dto.confirmedPassword = "hardPass1"

        // Save user and generate confirmation token
        facade.saveUserAndGenerateAccountConfirmationToken(dto)

        // Find it by email and it won't be a null
        Assertions.assertNotNull(facade.findUserByEmail(dto.email))

        // Find created user and confirmation token
        val user = facade.findUserByEmail(dto.email)
        val confirmationToken = facade.findConfirmationTokenByUser(user).confirmationToken

        // Check if confirmation token is not a null
        Assertions.assertNotNull(confirmationToken)

        // Check if user account is not enabled
        Assertions.assertFalse(user.isEnabled)

        // Confirm account token
        facade.confirmAccountToken(confirmationToken)

        // Find created user again
        // Check if found user account is enabled
        val userToCheck = facade.findUserByEmail(dto.email)
        Assertions.assertTrue(userToCheck.isEnabled)

        // Check if token has been deleted
        Assertions.assertTrue(!facade.isTokenExists(confirmationToken))
    }

    @Test fun `Should send reset password token for user and change his password`() {
        // Create ForgotLoginOrPasswordDTO
        val dto = ForgotLoginOrPasswordDTO()

        // Set forgotten account email
        dto.forgottenEmail = "helloimcopyoftruejohndoe@wp.pl"

        // Generate and save reset token
        facade.generateAndSaveResetConfirmationToken(dto)

        // Find user and reset token
        val user = facade.findUserByEmail("helloimcopyoftruejohndoe@wp.pl")
        val confirmationToken = facade.findConfirmationTokenByUser(user).confirmationToken

        // Check if reset token is not a null
        Assertions.assertNotNull(confirmationToken)

        // Confirm reset token
        facade.confirmResetToken(confirmationToken)

        // Set new password and delete token
        facade.setNewPasswordAndDeleteToken("hardPass2", confirmationToken)

        // Find user again,
        // check if found user password has been changed
        val userToCheck = facade.findUserByEmail("helloimcopyoftruejohndoe@wp.pl")
        Assertions.assertNotEquals(user.password, userToCheck.password)
    }

    @Test fun `Should check if login, email and phone already exists`() {
        Assertions.assertAll("are not unique",
                Executable { Assertions.assertEquals(false, facade.isLoginUnique("fakeJohnDoe")) },
                Executable { Assertions.assertEquals(false, facade.isEmailUnique("helloimcopyoftruejohndoe@wp.pl")) },
                Executable { Assertions.assertEquals(false, facade.isPhoneUnique("657236463")) }
        )
    }

    @Test fun `Should check if login, email and phone not exists`() {
        Assertions.assertAll("are unique",
                Executable { Assertions.assertEquals(true, facade.isLoginUnique("Joe1")) },
                Executable { Assertions.assertEquals(true, facade.isEmailUnique("joe@email.com")) },
                Executable { Assertions.assertEquals(true, facade.isPhoneUnique("509325455")) }
        )
    }

    @Test fun `Should validate if emails and confirmed emails are the same or not`() {
        // Create ChangeEmailDTO
        val dto = ChangeEmailDTO()

        // Set email and confirmed email
        dto.email = "ilovetocode@email.com"
        dto.confirmedEmail = "ilovetocode@email.com"

        // Create BindingResult
        val result = createBindingResult(dto)

        // Check if emails matches
        facade.checkConfirmedEmail(dto, result)

        // No errors cuz emails are the same
        Assertions.assertFalse(result.hasErrors())

        // Set email and confirmed email again
        dto.email = "ilovetocode@mail.com"
        dto.confirmedEmail = "iliketocode@mail.com"

        // Check if email matches again
        facade.checkConfirmedEmail(dto, result)

        // Error appear cuz emails are not the same
        Assertions.assertTrue(result.hasErrors())
    }

    @Test fun `Should validate if phone numbers and confirmed phone numbers are the same or not`() {
        // Create ChangePhoneNumberDTO
        val dto = ChangePhoneNumberDTO()

        // Set phone number and confirmed phone number
        dto.phoneNumber = "509498236"
        dto.confirmedPhoneNumber = "509498236"

        // Create BindingResult
        val result = createBindingResult(dto)

        // Check if phone numbers matches
        facade.checkConfirmedPhoneNumber(dto, result)

        // No errors cuz phone numbers are the same
        Assertions.assertFalse(result.hasErrors())

        // Set phone number and confirmed phone number again
        dto.phoneNumber = "111111111"
        dto.confirmedPhoneNumber = "222222222"

        // Check if phone numbers matches again
        facade.checkConfirmedPhoneNumber(dto, result)

        // Error appear cuz phone numbers are not the same
        Assertions.assertTrue(result.hasErrors())
    }

    @Test fun `Should validate if password and confirmed password are the same or not`() {
        // Create ChangePasswordDTO
        val dto = ChangePasswordDTO()

        // Set password and confirmed password
        dto.password = "hardPass1"
        dto.confirmedPassword = "hardPass1"

        // Create BindingResult
        val result = createBindingResult(dto)

        // Check if passwords matches
        facade.checkConfirmedPassword(dto, result)

        // No errors cuz passwords are the same
        Assertions.assertFalse(result.hasErrors())

        // Set password and confirmed password again
        dto.password = "hardPass23"
        dto.confirmedPassword = "hardPass5"

        // Check if passwords matches again
        facade.checkConfirmedPassword(dto, result)

        // Error appear cuz passwords are not the same
        Assertions.assertTrue(result.hasErrors())
    }

    @Test fun `Should change email, phone number and password`() {
        // Find user by email
        val user = facade.findUserByEmail("linus.torvalds@linux.com")

        // Change his email
        facade.changeEmail(user.id, "imHisNewEmail@email.com")

        // Change his phone number
        facade.changePhoneNumber(user.id, "409457502")

        // Change his password
        facade.changePassword(user.id, "hardPass1")

        // Find user by new email
        val userToCheck = facade.findUserByEmail("imHisNewEmail@email.com")

        Assertions.assertAll("are changed",
                Executable { Assertions.assertNotEquals(user.email, userToCheck.email) },
                Executable { Assertions.assertNotEquals(user.phoneNumber, userToCheck.phoneNumber) },
                Executable { Assertions.assertNotEquals(user.password, userToCheck.password) }
        )
    }

    private fun createBindingResult(obj: Any): BindingResult {
        return DataBinder(obj).bindingResult
    }
}
