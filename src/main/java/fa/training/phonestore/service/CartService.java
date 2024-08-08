package fa.training.phonestore.service;

import fa.training.phonestore.entity.Cart;
import fa.training.phonestore.entity.ProductInfo;

import java.util.List;

public interface CartService {
    void addToCart(Cart cart);
    List<ProductInfo> getProductInfoByCart(int customerID);
}
