package co.simplon.devbookapi.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = LinkUniqueValidator.class)
public @interface LinkUnique {

    String message() default "This Rss Provider already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
