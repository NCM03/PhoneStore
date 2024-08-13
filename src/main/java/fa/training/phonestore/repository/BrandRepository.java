package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Brand;
import fa.training.phonestore.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{
    Optional<Brand> findByBrandId(int id);
}
