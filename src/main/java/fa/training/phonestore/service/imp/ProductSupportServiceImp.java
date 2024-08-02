package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.*;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductSupportServiceImp {
    Page<ProductSupport> findProductSupportByProductId(int productId);
    List<ProductSupport> getHomeLastestProduct();
    Page<ProductSupport> getSearchProduct(String keyword,int page,int size);
    List<ProductSupport> getSearchList(String keyword);
}
