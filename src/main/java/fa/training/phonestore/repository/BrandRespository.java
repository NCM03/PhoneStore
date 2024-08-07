package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRespository extends JpaRepository<Brand, Integer> {
    Page<Brand> findAll(Pageable pageable);
    Page<Brand> findBrandByBrandNameContaining(String brandName, Pageable pageable);
    boolean deleteBrandByBrandID(int brandID);
    boolean existsBrandByBrandName(String brandName);
    List<Brand> findAll();
}
