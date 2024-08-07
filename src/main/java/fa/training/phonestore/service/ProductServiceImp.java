package fa.training.phonestore.service;

import fa.training.phonestore.entity.Product;
import fa.training.phonestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements  ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

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

    @Override
    public void changeSatus(int productId, int status) {
        Product product = productRepository.findByProductId(productId);
        product.setStatus(status);
        productRepository.save(product);
    }
    @Override
    public List<Product> getHomeTopRateProduct() {
        return productRepository.listTopRateProduct();
    }

    @Override
    public Page<Product> getHomeReviewProduct() {
        return productRepository.listReviewProducts(PageRequest.of(0, 6));
    }

    @Override
    public Page<Product> findByCategory(int categoryId, int page, int size) {
        return productRepository.findByCategory(categoryId,PageRequest.of(page, size));
    }
}
