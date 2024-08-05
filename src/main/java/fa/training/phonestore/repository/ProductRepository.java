package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM Product"
            , nativeQuery = true)
    Page<Product> listProductsFeature(Pageable pageable);

    @Query(value = "SELECT * FROM Product WHERE Product.Comment IS NOT NULL"
            , nativeQuery = true)
    Page<Product> listReviewProducts(Pageable pageable);

    @Query(value = "SELECT * FROM Product WHERE Product.Rating >= 4.5 " +
            "ORDER BY Product.Rating OFFSET 0 ROWS FETCH NEXT 2 ROWS ONLY",
            nativeQuery = true
    )
    List<Product> listTopRateProductSection1();

    @Query(value = "SELECT p FROM Product p WHERE p.categoryId = ?1 "
    )
    Page<Product> findByCategory(int categoryID,Pageable pageable);

}
