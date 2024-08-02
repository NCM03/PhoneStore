package fa.training.phonestore.Constraint.EntityConstraint.customerconstraint;

import fa.training.phonestore.validators.CustomerValidator.CustomerDOBValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  CustomerDOBValidator.class)
public @interface CustomerDateOfBirth {
    String message() default "Invalid Data";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
