package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductInfoServiceImp {
    Page<ProductInfo> findAll(Pageable pageable);
    List<ProductInfo> findAllByProductContainsIgnoreCase(String name,Pageable pageable);
    List<ProductInfo> getHomeLastestProduct();
    List<ProductInfo> getSearchProduct(String keyword);
    Page<ProductInfo> getSearchList(String keyword,int page, int size);
}
