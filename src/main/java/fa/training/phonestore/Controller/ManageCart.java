package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageCart {
    private final HttpSession httpSession;

    public ManageCart(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
//    @Autowired
//    Cart
     @GetMapping("/getCartNumber")
    public int getCartNumber() {
        Account account = (Account) httpSession.getAttribute("account");
   return  0;
    }
}
