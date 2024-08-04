package fa.training.phonestore.service;

import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoServiceIMP implements  ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public List<ProductInfo> findByProduct_ProductId(int productId) {
        return productInfoRepository.findByProduct_ProductId(productId);
    }

    @Override
    public Optional<ProductInfo> findById(int id) {
        return productInfoRepository.findById(id);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }
}
