package pl.robert.project.user.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import pl.robert.project.validation.LoginPasswordValidation;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class SignInDTO {

    @LoginPasswordValidation(fieldName = "Login")
    String login;

    @LoginPasswordValidation(fieldName = "Password")
    String password;
}
