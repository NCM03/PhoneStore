package fa.training.phonestore.validators.DtoValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.length()<6){
            return false;
        }else {
            return true;
        }
    }
}
