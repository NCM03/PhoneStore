package fa.training.phonestore.validators;

import fa.training.phonestore.Constraint.EntityConstraint.ImageNameConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches("^[a-zA-Z0-9]+\\.(jpg|png|jpeg)$");
    }
}
