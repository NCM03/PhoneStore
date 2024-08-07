package fa.training.phonestore.service;

import fa.training.phonestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    List<Product> findAll();
    Product findById(int id);
    Boolean findImageData(String imgData);
    void changeSatus(int productId, int status);
    List<Product> getHomeTopRateProduct();
    Page<Product> getHomeReviewProduct();
    Page<Product> findByCategory(int categoryId, int page, int size);

}
