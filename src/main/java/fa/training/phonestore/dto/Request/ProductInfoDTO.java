package fa.training.phonestore.dto.Request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductInfoDTO {
        // Getters and Setters
        private String group;
        private List<String> imageUrl;
        private String description;
        private String price;
        private String ram;
        private String capacity;
        private String color;
        private String quantity;
}

