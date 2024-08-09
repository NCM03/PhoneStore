package fa.training.phonestore.service;

import fa.training.phonestore.entity.Cart;
import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<ProductInfo> getProductInfoByCart(int customerID) {
        return cartRepository.getCartByCustomerId(customerID);
    }

    @Override
    public Cart getCartByCartId(int cartID) {
        return cartRepository.findCartByCartId(cartID);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
