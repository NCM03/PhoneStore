package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    Page<Brand> findAllBrands(Pageable pageable);
    Page<Brand> findBrandsByBrandNameContaining(String brandName, Pageable pageable);
    Brand saveBrand(Brand brand);
    boolean deleteBrandByBrandID(int brandID);
    boolean existsBrandByBrandName(String brandName);
    List<Brand> findAll();
}
