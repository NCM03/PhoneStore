package fa.training.phonestore.validators.CustomerValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Calendar;
import java.util.Date;

public class CustomerDOBValidator implements ConstraintValidator<CustomerDateOfBirth, Date> {
    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(value);

        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.YEAR, -18);

        return birthDate.before(currentDate);
    }
}
