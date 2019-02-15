package pl.robert.project.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.robert.project.validation.LoginPassword;

@NoArgsConstructor
@Getter @Setter
public class SignInDTO {

    @LoginPassword(fieldName = "Login")
    private String login;

    @LoginPassword(fieldName = "Password")
    private String password;
}
