package pl.robert.project.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginPasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginPassword {

    String fieldName();

    String message() default "Given login or password are incorrect OR your account is not verified by email.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
