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
    public List<ProductInfo> findAllByProductContainsIgnoreCase(String name, Pageable pageable) {
        return productInfoRepository.findAllByProductContainsIgnoreCase(name, pageable);
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
    public List<String> getColorByCapacity(int ram, int capacity, int productid) {
        return productInfoRepositoryNotPageble.getColorByCapacity(ram ,capacity, productid);
    }

    @Override
    public List<Integer> getCapacityByRam(int ram, int productId) {
        return productInfoRepositoryNotPageble.getCapacityByRam(ram, productId);
    }

    @Override
    public List<ProductInfo> getProductInfo( int ram, int capacity, int productId) {
        return productInfoRepositoryNotPageble.getProductInfoIdToCart(ram,capacity,productId);
    }
}
