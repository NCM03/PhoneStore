package fa.training.phonestore.service;

import fa.training.phonestore.entity.Cart;
import fa.training.phonestore.repository.CartRepository;
import fa.training.phonestore.service.imp.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }
}