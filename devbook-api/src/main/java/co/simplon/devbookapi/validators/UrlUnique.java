package co.simplon.devbookapi.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = UrlUniqueValidator.class)
public @interface UrlUnique {

    String message() default "This article already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
