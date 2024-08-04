package fa.training.phonestore.dto.Request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductEditDTO {
    private int id;
    private String productName;
    private String imageData;
    private String comment;
    private int categoryId;
    private int brandId;
}