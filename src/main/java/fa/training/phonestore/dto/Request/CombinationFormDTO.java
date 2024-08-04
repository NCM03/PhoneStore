package fa.training.phonestore.dto.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CombinationFormDTO {
    private ProductDTO productForm;
    private List<ProductInfoDTO> productSupForm;
}
