package pl.robert.project.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsUniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUniqueValidation {

    String fieldName();

    String message() default "{javax.validation.constraints.works.IsUniqueValidation.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
