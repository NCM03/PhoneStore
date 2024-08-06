package fa.training.phonestore.validators.DtoValidator;
import fa.training.phonestore.Constraint.EntityConstraint.DtoConstraint.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() <= 8) {
            return false;
        }
        boolean hasSpecialChar = false;
        boolean hasUppercase = false;

        for (char c : value.toCharArray()) {
            if (!hasSpecialChar && !Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
            if (!hasUppercase && Character.isUpperCase(c)) {
                hasUppercase = true;
            }

            if (hasSpecialChar && hasUppercase) {
                return true;
            }
        }

        return false;
    }
}
