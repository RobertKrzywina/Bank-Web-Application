package pl.robert.project.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginPasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginPasswordValidation {

    String fieldName();

    String message() default "{javax.validation.constraints.works.LoginPasswordValidation.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
