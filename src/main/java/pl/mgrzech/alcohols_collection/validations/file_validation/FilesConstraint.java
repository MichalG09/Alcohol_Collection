package pl.mgrzech.alcohols_collection.validations.file_validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A special validation check whether picture was added to alcohol.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FilesValidation.class)
public @interface FilesConstraint {
    String message() default "Brak zdjęcia głównego";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
