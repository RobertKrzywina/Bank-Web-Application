package pl.robert.project.app.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.robert.project.app.validation.LoginPassword;

@NoArgsConstructor
@Getter @Setter
public class SignInDto {

    @LoginPassword(fieldName = "Login")
    private String login;

    @LoginPassword(fieldName = "Password")
    private String password;
}
