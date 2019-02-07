package pl.robert.project.app.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
class ConfirmedPasswordValidator implements ConstraintValidator<ConfirmedPassword, String>, RegexExpressions {

    @Override
    public void initialize(ConfirmedPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
