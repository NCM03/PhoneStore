package fa.training.phonestore.entity;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class DTO {
    @Size(min = 6, message = "Username needs to be at least 6 characters long")
    private String username;
    private String password;

}
