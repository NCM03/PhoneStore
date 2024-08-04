package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductId(int id);

    @Query(value = "SELECT imageData FROM product WHERE imageData = :param", nativeQuery = true)
    String findImgData(@Param("param") String param);
}
