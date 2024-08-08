package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Cart;

import fa.training.phonestore.service.imp.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageCart {
    private final HttpSession httpSession;

    @Autowired
    private CartService cartService;
    public ManageCart(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
    //    @Autowired
//    Cart
    @GetMapping("/get-cart-number")
    public int getCartNumber() {
        Account account = (Account) httpSession.getAttribute("account");
        return  0;
    }
    @PostMapping("/add-to-cart")
    public void addToCart(@RequestParam("productInfo") int productInfoid, @RequestParam("quantity") int quantity, HttpSession session) {
        Account account = (Account) httpSession.getAttribute("account");
        Cart cart = new Cart();
        cart.getCustomer().setCustomerId(account.getAccountId());
        cart.getProductInfo().setProductInfoId(productInfoid);
        cart.setQuantity(quantity);
        cartService.addToCart(cart);
    }
}