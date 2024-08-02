package fa.training.phonestore.validators;

import fa.training.phonestore.Constraint.EntityConstraint.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.equals("Male") || value.equals("Female") || value.equals("Other");
    }
}
