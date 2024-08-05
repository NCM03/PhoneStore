package fa.training.phonestore.service;

import fa.training.phonestore.dto.request.PageableDTO;
import fa.training.phonestore.entity.Product;
import fa.training.phonestore.repository.ProductRepository;
import fa.training.phonestore.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getHomeTopRateProductSection1() {
        return productRepository.listTopRateProductSection1();
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
