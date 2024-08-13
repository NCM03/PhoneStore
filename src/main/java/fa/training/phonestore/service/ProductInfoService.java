package fa.training.phonestore.service;


import fa.training.phonestore.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductInfoService {
    List<ProductInfo> findByProduct_ProductId(int productId);
    Optional<ProductInfo> findById(int id);
    List<ProductInfo> findAll();
    Page<ProductInfo> findAll(Pageable pageable);
    Page<ProductInfo> findAllProductInfosByBrand(int brandid,int page, int size);
    List<ProductInfo> getHomeLastestProduct();
    List<ProductInfo> getSearchProduct(String keyword);
    Page<ProductInfo> getSearchList(String keyword,int page, int size);
    List<ProductInfo> getProductInfoDiscount();
    Page<ProductInfo> findAllByProductInfoNameAndCapacityAndRamAndPrice(String name,Integer capacity,Integer ram,Integer pricemin,Integer pricemax,Integer brandID, int page, int size);
}
