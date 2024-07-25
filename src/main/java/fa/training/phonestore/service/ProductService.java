package fa.training.phonestore.service;

import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductSupport;
import fa.training.phonestore.repository.ProductRepository;
import fa.training.phonestore.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> getHomeFeatureProduct (){
        return productRepository.listProductsFeature(PageRequest.of(0, 8));
    };

    @Override
    public List<Product> getHomeTopRateProductSection1() {
        return productRepository.listTopRateProductSection1();
    }

    @Override
    public List<Product> getHomeTopRateProductSection2() {
        return productRepository.listTopRateProductSection2();
    }

    @Override
    public List<Product> getHomeTopRateProductSection3() {
        return productRepository.listTopRateProductSection3();
    }

    @Override
    public Page<Product> getHomeReviewProduct() {
        return productRepository.listReviewProducts(PageRequest.of(0, 6));
    }


}
