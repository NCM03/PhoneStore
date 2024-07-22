package fa.training.phonestore.Constraint.EntityConstraint;
import fa.training.phonestore.Validators.EmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  EmailValidator.class)
public @interface EmailConstraint {
    String message() default "Invalid Data";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
