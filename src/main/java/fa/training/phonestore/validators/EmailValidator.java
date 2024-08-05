package fa.training.phonestore.Validators;


import fa.training.phonestore.Constraint.EntityConstraint.EmailConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator <EmailConstraint,String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return value.matches(emailRegex);
    }
}
