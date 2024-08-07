package fa.training.phonestore.service;

import fa.training.phonestore.entity.Brand;
import fa.training.phonestore.repository.BrandRespository;
import fa.training.phonestore.service.imp.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRespository brandRepository;

    @Override
    public Page<Brand> findAllBrands(Pageable pageable) {
        return brandRepository.findAll(pageable);

    }

    @Override
    public Page<Brand> findBrandsByBrandNameContaining(String brandName, Pageable pageable) {
        return brandRepository.findBrandByBrandNameContaining(brandName, pageable);
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }
    @Override
    public boolean deleteBrandByBrandID(int brandID) {
        return brandRepository.deleteBrandByBrandID(brandID);
    }
    @Override
    public boolean existsBrandByBrandName(String brandName) {
        return brandRepository.existsBrandByBrandName(brandName);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
