package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    @Query(value = "SELECT * FROM Product \n"+
//            "LEFT JOIN product_support ON Product.productId = product_support.productID",
//            nativeQuery = true
//    )
//    Page<Product> listAllProductsPrice(@Param("ProductID") int ProductID, Pageable pageable);

    @Query(value = "SELECT * FROM Product WHERE Product.productId < 9"
            , nativeQuery = true)
    Page<Product> listProductsFeature(Pageable pageable);

    @Query(value = "SELECT * FROM Product WHERE Product.Comment"
            , nativeQuery = true)
    Page<Product> listReviewProducts(Pageable pageable);

    @Query(value = "SELECT * FROM Product \n" +
            "WHERE Product.Rating > 4.5",
            nativeQuery = true
    )
    Page<Product> listTopRateProduct(Pageable pageable);
}
