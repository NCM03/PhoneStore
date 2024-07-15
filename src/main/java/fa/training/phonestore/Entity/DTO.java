package fa.training.phonestore.Entity;

import fa.training.phonestore.Entity.EntityConstraint.Password;
import fa.training.phonestore.Entity.EntityConstraint.Username;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DTO {
        @Size(min = 6, message = "Username needs to be at least 6 characters long")
    private String username;
       @Password(message = "Password have at least 8 characters long , 1 special  characters and 1 uppercase characters")
    private String password;

    public DTO() {
        super();
    }
}
