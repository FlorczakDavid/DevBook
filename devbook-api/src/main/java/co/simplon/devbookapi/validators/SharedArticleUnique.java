package co.simplon.devbookapi.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy = SharedArticleUniqueValidator.class)
public @interface SharedArticleUnique {

    String message() default "This article already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
