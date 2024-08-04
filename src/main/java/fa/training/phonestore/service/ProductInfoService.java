package fa.training.phonestore.service;

import fa.training.phonestore.entity.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductInfoService {
    List<ProductInfo> findByProduct_ProductId(int productId);
    Optional<ProductInfo> findById(int id);
    List<ProductInfo> findAll();
}
