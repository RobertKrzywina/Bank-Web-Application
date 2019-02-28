package pl.robert.project.user.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import pl.robert.project.validation.LoginPassword;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class SignInDTO {

    @LoginPassword(fieldName = "Login")
    String login;

    @LoginPassword(fieldName = "Password")
    String password;
}
