package fa.training.phonestore.service;

import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.repository.ProductInfoRepository;
import fa.training.phonestore.repository.ProductInfoRepositoryNotPageble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoServiceImp implements  ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ProductInfoRepositoryNotPageble productInfoRepositoryNotPageble;

    @Override
    public List<ProductInfo> findByProduct_ProductId(int productId) {
        return productInfoRepository.findByProduct_ProductId(productId);
    }

    @Override
    public Optional<ProductInfo> findById(int id) {
        return productInfoRepositoryNotPageble.findById(id);
    }

    @Override
    public List<ProductInfo> findAll() {

        return productInfoRepositoryNotPageble.findAll();
    }
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public Page<ProductInfo> findAllProductInfosByBrand(int brandid,int page, int size) {
        return productInfoRepository.findAllByBrand(brandid,PageRequest.of(page, size));
    }

    @Override
    public List<ProductInfo> getHomeLastestProduct(){
        return productInfoRepository.listLastestProduct();
    }

    @Override
    public List<ProductInfo> getSearchProduct(String keyword) {
        return productInfoRepository.Search(keyword);
    }

    @Override
    public Page<ProductInfo> getSearchList(String keyword, int page, int size) {
        return productInfoRepository.searchList(keyword, PageRequest.of(page, size));
    }

    @Override
    public List<ProductInfo> getProductInfoDiscount() {
        return productInfoRepositoryNotPageble.getProductInfoDiscount();
    }

    @Override
    public Page<ProductInfo> findAllByProductInfoNameAndCapacityAndRamAndPrice(String name, Integer capacity, Integer ram, Integer pricemin, Integer pricemax,Integer brandID, int page, int size) {
        return productInfoRepository.findAllByProductInfoNameAndCapacityAndRamAndPrice(name,capacity,ram,pricemin,pricemax,brandID,PageRequest.of(page, size));
    }
}
