package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>{

    List<ProductInfo> findByProduct_ProductId(int productId);

    @Query(value = "SELECT * FROM product_info \n" +
            "ORDER BY product_info.ImportDate DESC OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY",
            nativeQuery = true
    )
    List<ProductInfo> listLastestProduct();

    @Query(value = "SELECT * FROM product_info WHERE product_info.ProductInfoName LIKE %?1%",
            nativeQuery = true
    )
    List<ProductInfo> Search(String keyword);

    @Query(value = "SELECT p FROM ProductInfo p WHERE p.productInfoName LIKE %?1%"
    )
    Page<ProductInfo> searchList(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM product_info " +
            "JOIN Product ON product_info.productID = Product.ProductID JOIN Brand ON Brand.BrandID = Product.BranchID " +
            "WHERE Brand.BrandID = ?1",nativeQuery = true

    )
    Page<ProductInfo> findAllByBrand(int brandID, Pageable pageable);

    @Query(value = "SELECT p FROM ProductInfo p " +
            "JOIN p.product prod " +
            "JOIN Brand b ON prod.brandId = b.brandId " +
            "WHERE (:keyword IS NULL OR :keyword = '' OR p.productInfoName LIKE %:keyword%) " +
            "AND (:capacity IS NULL OR p.capacity = :capacity) " +
            "AND (:ram IS NULL OR p.ram = :ram) " +
            "AND (:priceMin IS NULL OR p.price - (p.price*p.disCount/100) >= :priceMin) " +
            "AND (:priceMax IS NULL OR p.price - (p.price*p.disCount/100) <= :priceMax) " +
            "AND (:brand IS NULL OR b.brandId = :brand)")
    Page<ProductInfo> findAllByProductInfoNameAndCapacityAndRamAndPrice(
            @Param("keyword") String keyword,
            @Param("capacity") Integer capacity,
            @Param("ram") Integer ram,
            @Param("priceMin") Integer priceMin,
            @Param("priceMax") Integer priceMax,
            @Param("brand") Integer brand,
            Pageable pageable);


}
