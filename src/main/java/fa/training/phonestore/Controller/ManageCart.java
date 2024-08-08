package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Cart;
import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class ManageCart {
    private final HttpSession httpSession;
    @Autowired
    CartService cartService;
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
    @GetMapping("")
    public String Cart(Model model) {
        if(httpSession.getAttribute("id")==null){
          return "redirect:/login"  ;
        }else{
        int customerId = (int) httpSession.getAttribute("id");

        List<ProductInfo> productInfoList = cartService.getProductInfoByCart(customerId);
        model.addAttribute("productInfo", productInfoList);
        return "shoping-cart";
    }
    }
    //    @PostMapping("/addToCart/{quantity}/{productInfoId}")
//    public Boolean addToCart(@PathVariable("quantity") int quantity, @PathVariable("productInfoId") int productInfoId) {
//        HttpSession session = httpSession;
//        if(session.getAttribute("id") == null) {
//            return false;
//        } else {
//            String customerId = session.getAttribute("id").toString();
//            Cart cart = new Cart();
//
//            // Khởi tạo đối tượng Customer và ProductInfo
//            Customer customer = new Customer();
//            customer.setCustomerId(Integer.parseInt(customerId));
//            ProductInfo productInfo = new ProductInfo();
//            productInfo.setProductInfoId(productInfoId);
//
//            // Thiết lập các giá trị cho đối tượng Cart
//            cart.setCustomer(customer);
//            cart.setQuantity(quantity);
//            cart.setProductInfo(productInfo);
//
//            // Lưu đối tượng Cart vào cơ sở dữ liệu
//            cartRepository.save(cart);
//            return true;
//        }
//    }
}
