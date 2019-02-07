package pl.robert.project.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfirmedPasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmedPassword {
    String message() default "Confirmed password not match password.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
