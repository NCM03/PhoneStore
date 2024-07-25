package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ProductSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductSupportRepository extends JpaRepository<ProductSupport, Integer> {

    @Query(value = "SELECT * FROM product_support \n" +
                   "LEFT JOIN Product ON product_support.productID = Product.productId ",
            nativeQuery = true
    )
    Page<ProductSupport> listAllProductsPrice(@Param("ProductID") int ProductID, Pageable pageable);

    @Query(value = "SELECT * FROM product_support \n" +
            "ORDER BY product_support.ImportDate DESC OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY",
            nativeQuery = true
    )
    List<ProductSupport> listLastestProduct();

    @Query(value = "SELECT * FROM product_support WHERE product_support.ProductSupName LIKE '%?1%'",
            nativeQuery = true
    )
    Page<ProductSupport> Search(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM product_support WHERE product_support.ProductSupName LIKE '%?1%'",
            nativeQuery = true
    )
    List<ProductSupport> searchList(String keyword);
}
