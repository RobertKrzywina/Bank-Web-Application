package pl.robert.project.app.user.domain.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String>, RegexExpressions {

    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return phoneNumber != null && phoneNumber.matches(phoneNumberRegex);
    }
}
