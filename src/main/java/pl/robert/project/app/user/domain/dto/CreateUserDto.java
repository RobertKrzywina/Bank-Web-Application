package pl.robert.project.app.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.robert.project.app.validation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
@NoArgsConstructor
@Getter @Setter
public class CreateUserDto implements RegexExpressions {

    @IsUnique(fieldName = "Login")
    @Size(min = 2, max = 12, message = "{login.size}")
    @NotEmpty(message = "{login.notEmpty}")
    private String login;

    @IsUnique(fieldName = "Email")
    @Pattern(regexp = emailRegex, message = "{email.wrongFormat}")
    @NotEmpty(message = "{email.notEmpty}")
    private String email;

    @IsUnique(fieldName = "Phone number")
    @Pattern(regexp = phoneNumberRegex, message = "{phoneNumber.wrongFormat}")
    @NotEmpty(message = "{phoneNumber.notEmpty}")
    private String phoneNumber;

    @Size(min = 5, max = 15, message = "{password.size}")
    @NotEmpty(message = "{password.notEmpty}")
    private String password;

    @ConfirmedPassword
    private String confirmedPassword;
}
