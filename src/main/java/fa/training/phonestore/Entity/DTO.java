package fa.training.phonestore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DTO {

    private String username;
    private String password;

    public DTO() {
        super();
    }
}
