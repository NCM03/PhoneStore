package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Repository
public interface ProductInfoRepository extends PagingAndSortingRepository<ProductInfo, Integer>, JpaSpecificationExecutor<ProductInfo> {

    List<ProductInfo> findByProduct_ProductId(int productId);
    @Override
    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findAllByProductContainsIgnoreCase(String name,Pageable pageable);

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

//    Page<ProductInfo> findAllByCapacityAndRam(Specification<ProductInfo> spec,Pageable pageable);
}
