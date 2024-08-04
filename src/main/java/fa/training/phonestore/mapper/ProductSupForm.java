package fa.training.phonestore.mapper;

import fa.training.phonestore.dto.Request.ProductInfoDTO;

import java.util.List;

public class ProductSupForm {
        private List<ProductInfoDTO> productSup;

        // Getters and setters
        public List<ProductInfoDTO> getProductSup() {
            return productSup;
        }

        public void setProductSup(List<ProductInfoDTO> productSup) {
            this.productSup = productSup;
        }
    }

