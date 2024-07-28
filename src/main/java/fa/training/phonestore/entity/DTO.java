package fa.training.phonestore.entity;

import fa.training.phonestore.Constraint.EntityConstraint.dtoconstraint.Password;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class DTO {
        @Size(min = 6, message = "Username needs to be at least 6 characters long")
    private String username;
       @Password(message = "Password have at least 8 characters long , 1 special  characters and 1 uppercase characters")
    private String password;

}
