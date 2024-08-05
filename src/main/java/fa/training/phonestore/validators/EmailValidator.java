package fa.training.phonestore.validators;


import fa.training.phonestore.Constraint.EntityConstraint.EmailConstraint;
import fa.training.phonestore.service.imp.CustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailValidator implements ConstraintValidator <EmailConstraint,String>{
   @Autowired
    private CustomerService customerService; //

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            addConstraintViolation(context, "Email không được để trống");
            return false;
        }

        // Kiểm tra định dạng email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!value.matches(emailRegex)) {
            addConstraintViolation(context, "Email không hợp lệ");
            return false;
        }

        // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa


        return true; // Email hợp lệ
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
