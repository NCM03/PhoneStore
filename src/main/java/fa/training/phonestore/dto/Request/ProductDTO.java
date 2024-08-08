package fa.training.phonestore.dto.Request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDTO {
    private String productName;
    private String imageData;
    private String comment;
    private int categoryId;
    private int brandId;
}
