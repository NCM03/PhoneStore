package fa.training.phonestore.mapper;

import fa.training.phonestore.dto.Request.ProductDTO;
import fa.training.phonestore.entity.Product;

public class ProductMapper {
    public static Product convertToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setComment(productDTO.getComment());
        product.setImageData(productDTO.getImageData());
        product.setCategoryId(productDTO.getCategoryId());
        product.setBrandId(productDTO.getBrandId());
        product.setProductName(productDTO.getProductName());
        return product;
    }
}
