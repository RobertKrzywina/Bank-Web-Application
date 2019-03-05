package pl.robert.project.validation;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import pl.robert.project.user.domain.UserFacade;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@SuppressWarnings("FieldCanBeLocal")
@FieldDefaults(level = AccessLevel.PRIVATE)
class LoginPasswordValidator implements ConstraintValidator<LoginPasswordValidation, String> {

    String fieldName;
    final UserFacade userFacade;

    static String login;
    static String password;
    static boolean isLoginCorrect;
    static boolean isPasswordCorrect;

    @Autowired
    public LoginPasswordValidator(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public void initialize(LoginPasswordValidation constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (fieldName.equals("Login")) {
            login = value;
            isLoginCorrect = userFacade.isLoginExists(login);
            return true;
        } else if (fieldName.equals("Password")) {
            password = value;
            isPasswordCorrect = userFacade.isLoginAndPasswordCorrect(login, password);
        }

        login = null;
        password = null;

        return isLoginCorrect && isPasswordCorrect;
    }
}
