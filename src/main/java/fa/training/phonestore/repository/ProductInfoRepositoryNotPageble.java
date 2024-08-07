package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ProductImage;
import fa.training.phonestore.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoRepositoryNotPageble extends JpaRepository<ProductInfo, Integer> {
    @Query(value = "SELECT p.productInfoId FROM ProductInfo p WHERE p.color LIKE  %?1% AND p.ram = ?2 AND p.capacity = ?3")
     ProductInfo getProductInfoId(int color, int ram, int capacity);

    @Query(value = "SELECT DISTINCT p.color FROM ProductInfo p WHERE p.ram= :ram AND p.capacity = :capacity AND p.product.productId = :productId And p.quantity > 0")
    List<String> getColorByCapacity(@Param("ram") int ram, @Param("capacity") int capacity, @Param("productId") int productId);

    @Query(value = "SELECT DISTINCT p.capacity FROM ProductInfo p WHERE p.ram = :ram AND p.product.productId = :productId And p.quantity > 0")
    List<Integer> getCapacityByRam(@Param("ram") int ram, @Param("productId") int productId);

    @Query(value = "SELECT p FROM ProductInfo p WHERE p.ram = :ram AND p.capacity = :capacity AND p.color LIKE 'Đen' AND p.product.productId = :productId AND p.quantity > 0")
    ProductInfo getProductInfoIdToCart2( @Param("ram") int ram, @Param("capacity") int capacity, @Param("productId") int productId);

    @Query(value = "SELECT p FROM ProductInfo p WHERE p.ram = :ram AND p.capacity = :capacity AND p.product.productId = :productId AND p.quantity > 0")
    List<ProductInfo> getProductInfoIdToCart( @Param("ram") int ram, @Param("capacity") int capacity, @Param("productId") int productId);
}
