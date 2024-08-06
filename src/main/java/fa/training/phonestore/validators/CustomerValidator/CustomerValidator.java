package fa.training.phonestore.validators.CustomerValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CustomerValidator implements ConstraintValidator<CustomerName,String> {
    private static final String NAME_PATTERN = "^[\\p{L}]+(\\s[\\p{L}]+)*$";
    private static final Pattern PATTERN = Pattern.compile(NAME_PATTERN);

    @Override
    public void initialize(CustomerName  constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null) {
            return false; // hoặc true, tùy thuộc vào yêu cầu của bạn
        }
        return PATTERN.matcher(name.trim()).matches();
    }
}
