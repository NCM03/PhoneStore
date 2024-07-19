package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ProductSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductSupportRepository extends JpaRepository<ProductSupport, Integer> {

    @Query(value = "SELECT * FROM product_support \n" +
                   "LEFT JOIN Product ON product_support.productID = Product.productId ",
            nativeQuery = true
    )
    Page<ProductSupport> listAllProductsPrice(@Param("ProductID") int ProductID, Pageable pageable);

    @Query(value = "SELECT * FROM product_support \n" +
            "ORDER BY product_support.ImportDate DESC ",
            nativeQuery = true
    )
    Page<ProductSupport> listLastestProduct( Pageable pageable);
}
