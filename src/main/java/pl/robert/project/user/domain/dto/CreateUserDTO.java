package pl.robert.project.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.robert.project.bank_account.domain.BankAccount;
import pl.robert.project.validation.IsUnique;
import pl.robert.project.validation.RegexExpressions;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter @Setter
public class CreateUserDTO implements RegexExpressions {

    @IsUnique(fieldName = "Login")
    @Size(min = 2, max = 12, message = "{login.size}")
    @NotEmpty(message = "{login.notEmpty}")
    private String login;

    @IsUnique(fieldName = "Email")
    @Pattern(regexp = emailRegex, message = "{email.wrongFormat}")
    @NotEmpty(message = "{email.notEmpty}")
    private String email;

    @IsUnique(fieldName = "Phone")
    @Pattern(regexp = phoneNumberRegex, message = "{phoneNumber.wrongFormat}")
    @NotEmpty(message = "{phoneNumber.notEmpty}")
    private String phoneNumber;

    @Size(min = 5, max = 15, message = "{password.size}")
    @NotEmpty(message = "{password.notEmpty}")
    private String password;

    private String confirmedPassword;

    private BankAccount bankAccount;
}
