package pl.robert.project.user.domain;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import pl.robert.project.bank.account.BankAccount;
import pl.robert.project.bank.account.BankAccountFacade;
import pl.robert.project.user.domain.dto.*;
import pl.robert.project.user.query.UserQuery;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserValidation validation;
    BankAccountFacade bankAccountFacade;
    TokenService tokenService;
    UserService userService;

    public void saveUserAndGenerateAccountConfirmationToken(CreateUserDTO dto) {
        userService.setPhoneNumber(dto);
        BankAccount account = bankAccountFacade.generateBankAccount();
        bankAccountFacade.save(account);
        User user = userService.create(dto);
        userService.save(user);
        tokenService.generateAccountConfirmationToken(user);
    }

    public void generateAndSaveResetConfirmationToken(ForgotLoginOrPasswordDTO dto) {
        tokenService.generateAndSaveResetConfirmationToken(userService.findByEmail(dto.getForgottenEmail()));
    }

    public boolean confirmAccountToken(String confirmationToken) {
        return tokenService.confirmAccountToken(confirmationToken);
    }

    public boolean confirmResetToken(String confirmationToken) {
        return tokenService.confirmResetToken(confirmationToken);
    }

    public boolean checkIfTokenAlreadySent(ForgotLoginOrPasswordDTO dto) {
        return tokenService.checkIfTokenAlreadySent(dto);
    }

    public void setNewPasswordAndDeleteToken(String newPassword, String confirmationToken) {
        ConfirmationToken token = tokenService.findByConfirmationToken(confirmationToken);
        User user = userService.findByEmail(token.getUser().getEmail());
        userService.changePassword(user.getId(), newPassword);
        tokenService.deleteToken(confirmationToken);
    }

    public boolean isLoginUnique(String login) {
        return userService.isLoginUnique(login);
    }

    public boolean isEmailUnique(String email) {
        return userService.isEmailUnique(email);
    }

    public boolean isPhoneUnique(String phone) {
        return userService.isPhoneUnique(phone);
    }

    public boolean isTokenExists(String token) {
        return tokenService.isTokenExists(token);
    }

    public boolean isLoginAndPasswordCorrect(String login, String password) {
        return userService.isLoginAndPasswordCorrect(login, password);
    }

    public UserQuery QueryByLogin(String login) throws NullPointerException {
        return userService.queryByLogin(login);
    }

    public ImmutableMap<String, String> initializeMapWithUserDetails(UserQuery userQuery) {
        return userService.initializeMapWithUserDetails(userQuery);
    }

    public AuthorizationDTO findByLogin(String login) throws NullPointerException {
        return userService.findByLogin(login);
    }

    public long findIdByLogin(String login) {
        return userService.findIdByLogin(login);
    }

    public User findUserByEmail(String email) {
        return userService.findByEmail(email);
    }

    public ConfirmationToken findConfirmationTokenByUser(User user) {
        return tokenService.findConfirmationTokenByUser(user);
    }

    public Page<User> findAll(int page, int size) {
        return userService.findAll(page, size);
    }

    public void deleteById(long id) {
        userService.deleteById(id);
    }

    public void changeEmail(long userId, String newEmail) {
        userService.changeEmail(userId, newEmail);
    }

    public void checkConfirmedEmail(ChangeEmailDTO dto, BindingResult result) {
        validation.checkConfirmedEmail(dto, result);
    }

    public void changePhoneNumber(long userId, String newPhoneNumber) {
        userService.changePhoneNumber(userId, newPhoneNumber);
    }

    public void checkConfirmedPhoneNumber(ChangePhoneNumberDTO dto, BindingResult result) {
        validation.checkConfirmedPhoneNumber(dto, result);
    }

    public void changePassword(long userId, String newPassword) {
        userService.changePassword(userId, newPassword);
    }

    public void checkConfirmedPassword(Object obj, BindingResult result) {
        validation.checkConfirmedPassword(obj, result);
    }

    public void checkForgottenEmail(boolean tokenAlreadySent, ForgotLoginOrPasswordDTO dto, BindingResult result) {
        validation.checkForgottenEmail(tokenAlreadySent, dto, result);
    }
}
