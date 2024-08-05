package fa.training.phonestore.Constraint.EntityConstraint.CustomerConstraint;

import fa.training.phonestore.validators.CustomerValidator.CustomerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  CustomerValidator.class)
public @interface CustomerName {
    String message() default "Invalid Data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
