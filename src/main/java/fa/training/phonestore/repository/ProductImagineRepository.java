package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImagineRepository extends JpaRepository<ProductImage, Integer> {
//    @Query(value = "SELECT p FROM Product p WHERE p.categoryId = ?1 "
//    )
//    List<ProductImage> findByProductSupID(int productSupID);
}
