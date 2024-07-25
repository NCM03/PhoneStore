package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImagineRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findProductImagesByProductID(int productID);
}
