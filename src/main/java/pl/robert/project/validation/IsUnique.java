package pl.robert.project.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsUniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUnique {

    String fieldName();

    String message() default "{fieldName} already exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
