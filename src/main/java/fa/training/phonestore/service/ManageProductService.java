package fa.training.phonestore.service;

import fa.training.phonestore.dto.Request.ProductInfoDTO;
import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductImage;
import fa.training.phonestore.entity.ProductInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface ManageProductService {
    public List<ProductImage> saveImages(List<String> imageNames) ;
    public void saveProductInfo(Product product, List<ProductInfoDTO> productSupList, MultipartFile[] fileSupProduct) ;
    public void saveProduct(Product product) ;
    public void replaceProduct(Product newProduct);
    public void replaceProductInfo(ProductInfo newProductInfo, String newUrl, int imageId);
    }
