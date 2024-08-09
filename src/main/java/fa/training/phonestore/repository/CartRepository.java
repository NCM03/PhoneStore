package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Cart;
import fa.training.phonestore.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT c.productInfo FROM Cart c WHERE c.customer.customerId = :customerId" )
    List<ProductInfo> getCartByCustomerId(@Param("customerId") int custumerId);
    Cart findCartByCartId(int cartId);

}
