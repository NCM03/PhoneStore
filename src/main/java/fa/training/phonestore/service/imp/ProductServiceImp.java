package fa.training.phonestore.service.imp;


import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductSupport;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductServiceImp {
//    Page<Product> getHomeFeatureProduct();
//    List<Product> getHomeLastestProduct();
    Page<Product> getHomeTopRateProduct();
    Page<Product> getHomeReviewProduct();

}
