package fa.training.phonestore.service;

import fa.training.phonestore.entity.Product;
import fa.training.phonestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceIMP implements  ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findByProductId(id);
    }

    @Override
    public Boolean findImageData(String imgData) {
        return Boolean.valueOf(productRepository.findImgData(imgData));
    }
}
