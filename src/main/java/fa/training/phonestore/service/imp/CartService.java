package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    void addToCart(Cart cart);
}

