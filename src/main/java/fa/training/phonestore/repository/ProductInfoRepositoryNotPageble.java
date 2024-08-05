package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ProductImage;
import fa.training.phonestore.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepositoryNotPageble extends JpaRepository<ProductInfo, Integer> {
}
