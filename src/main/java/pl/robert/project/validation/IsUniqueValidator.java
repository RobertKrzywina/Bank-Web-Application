package pl.robert.project.validation;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import pl.robert.project.user.domain.UserFacade;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@FieldDefaults(level = AccessLevel.PRIVATE)
class IsUniqueValidator implements ConstraintValidator<IsUnique, String>, RegexExpressions {

    String fieldName;
    final UserFacade userFacade;

    @Autowired
    public IsUniqueValidator(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public void initialize(IsUnique constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        switch (fieldName) {
            case "Login": return userFacade.isLoginUnique(value);
            case "Email": return userFacade.isEmailUnique(value);
            case "Phone": return userFacade.isPhoneUnique(value);
        }
        return false;
    }
}
