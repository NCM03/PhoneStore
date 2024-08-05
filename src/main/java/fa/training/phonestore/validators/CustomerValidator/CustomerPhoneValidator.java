package fa.training.phonestore.Validators.CustomerValidator;

import fa.training.phonestore.Constraint.EntityConstraint.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerPhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches("^\\d{10}$");
    }
}
