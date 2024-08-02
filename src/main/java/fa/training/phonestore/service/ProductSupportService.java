package fa.training.phonestore.service;


import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductSupport;
import fa.training.phonestore.repository.ProductRepository;
import fa.training.phonestore.repository.ProductSupportRepository;
import fa.training.phonestore.service.imp.ProductSupportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSupportService implements ProductSupportServiceImp {

    @Autowired
    ProductSupportRepository productSupportRepository;

    @Override
    public Page<ProductSupport> findProductSupportByProductId(int productId) {
        return productSupportRepository.listAllProductsPrice(productId,PageRequest.of(0, 8));
    }

    @Override
    public List<ProductSupport> getHomeLastestProduct(){
        return productSupportRepository.listLastestProduct();
    }

    @Override
    public Page<ProductSupport> getSearchProduct(String keyword,int page,int size) {
        return productSupportRepository.Search(keyword,PageRequest.of(page, size));
    }

    @Override
    public List<ProductSupport> getSearchList(String keyword) {
        return productSupportRepository.searchList(keyword);
    }


}
