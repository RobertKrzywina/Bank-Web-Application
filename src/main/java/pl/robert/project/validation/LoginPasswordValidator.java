package pl.robert.project.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.robert.project.user.domain.UserFacade;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@SuppressWarnings("FieldCanBeLocal")
class LoginPasswordValidator implements ConstraintValidator<LoginPassword, String> {

    private String fieldName;
    private UserFacade userFacade;

    private static String login;
    private static String password;
    private static boolean isLoginCorrect;
    private static boolean isPasswordCorrect;
    private static boolean isBothCorrect;

    @Autowired
    public LoginPasswordValidator(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public void initialize(LoginPassword constraintAnnotation) {
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
        isBothCorrect = false;

        if (isLoginCorrect && isPasswordCorrect) isBothCorrect = true;

        return isBothCorrect;
    }
}
