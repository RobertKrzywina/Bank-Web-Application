package pl.robert.project.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class IsUniqueValidator implements ConstraintValidator<IsUnique, String>, RegexExpressions {

    private String fieldName;

    @Override
    public void initialize(IsUnique constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
