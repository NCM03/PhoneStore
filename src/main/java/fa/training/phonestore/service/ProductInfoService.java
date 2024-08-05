package fa.training.phonestore.service;


import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.repository.ProductInfoRepository;
import fa.training.phonestore.service.imp.ProductInfoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoService implements ProductInfoServiceImp {

    @Autowired
    ProductInfoRepository productInfoRepository;

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
        return productInfoRepository.searchList(keyword,PageRequest.of(page, size));
    }


}
