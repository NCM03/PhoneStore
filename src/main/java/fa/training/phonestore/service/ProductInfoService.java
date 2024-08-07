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
    List<ProductInfo> findAllByProductContainsIgnoreCase(String name,Pageable pageable);
    List<ProductInfo> getHomeLastestProduct();
    List<ProductInfo> getSearchProduct(String keyword);
    Page<ProductInfo> getSearchList(String keyword,int page, int size);
    List<String> getColorByCapacity(int ram, int capacity,int productId);
    List<Integer> getCapacityByRam(int ram,int productId);
    List<ProductInfo> getProductInfo( int ram, int capacity,int productId);
}
