package pl.robert.project.user.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import pl.robert.project.bank.account.BankAccount;
import pl.robert.project.validation.IsUniqueValidation;
import pl.robert.project.validation.RegexExpressions;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class CreateUserDTO implements RegexExpressions {

    @IsUniqueValidation(fieldName = "Login")
    @Size(min = 2, max = 12, message = "{login.size}")
    @NotEmpty(message = "{login.notEmpty}")
    String login;

    @IsUniqueValidation(fieldName = "Email")
    @Pattern(regexp = EMAIL_REGEX, message = "{email.wrongFormat}")
    @NotEmpty(message = "{email.notEmpty}")
    String email;

    @IsUniqueValidation(fieldName = "Phone")
    @Pattern(regexp = PHONE_NUMBER_REGEX, message = "{phoneNumber.wrongFormat}")
    @NotEmpty(message = "{phoneNumber.notEmpty}")
    String phoneNumber;

    @Size(min = 5, max = 15, message = "{password.size}")
    @NotEmpty(message = "{password.notEmpty}")
    String password;

    String confirmedPassword;

    BankAccount bankAccount;
}
