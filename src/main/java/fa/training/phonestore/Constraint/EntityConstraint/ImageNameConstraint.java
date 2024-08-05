package fa.training.phonestore.Constraint.EntityConstraint;

import fa.training.phonestore.validators.EmailValidator;
import fa.training.phonestore.validators.ImageNameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  ImageNameValidator.class)
public @interface ImageNameConstraint {
    String message() default "Invalid Data";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
