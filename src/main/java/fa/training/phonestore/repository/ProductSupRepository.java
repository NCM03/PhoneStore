package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ProductStatus;
import fa.training.phonestore.entity.ProductSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSupRepository extends JpaRepository<ProductSupport, Integer> {
    List<ProductSupport> findByProductID(Integer productId);
}
