package fa.training.phonestore.service;

import fa.training.phonestore.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Boolean findImageData(String imgData);
    void changeSatus(int productId, int status);
}
