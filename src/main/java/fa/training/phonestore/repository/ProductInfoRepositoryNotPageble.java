package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ProductImage;
import fa.training.phonestore.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepositoryNotPageble extends JpaRepository<ProductInfo, Integer> {
    @Query(value = "SELECT p.productInfoId FROM ProductInfo p WHERE p.color LIKE  %?1% AND p.ram = ?2 AND p.capacity = ?3")
    public void getProductInfoId(int color, int ram, int capacity);
}
