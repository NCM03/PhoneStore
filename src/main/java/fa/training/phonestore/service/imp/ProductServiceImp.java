package fa.training.phonestore.service.imp;


import fa.training.phonestore.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductServiceImp {
//    Page<Product> getHomeFeatureProduct();
//    List<Product> getHomeLastestProduct();
    List<Product> getHomeTopRateProductSection1();
    List<Product> getHomeTopRateProductSection2();
    List<Product> getHomeTopRateProductSection3();
    Page<Product> getHomeReviewProduct();

}
