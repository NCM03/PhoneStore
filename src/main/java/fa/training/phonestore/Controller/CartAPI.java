package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Cart;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.repository.CartRepository;
import fa.training.phonestore.repository.ProductInfoRepositoryNotPageble;
import fa.training.phonestore.service.ProductInfoService;
import fa.training.phonestore.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartAPI {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductInfoRepositoryNotPageble productInfoRepositoryNotPageble;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/select-ram/{ram}/{productId}")
    public List<Integer> getCapacityByRam(@PathVariable("ram") int ram, @PathVariable("productId") int productId) {
        List<Integer> capacities = productInfoService.getCapacityByRam(ram, productId);
        return capacities;
    }

    @GetMapping("/select-capacity/{ram}/{capacity}/{productId}")
    public List<String> getColorsByCapacity(@PathVariable("ram") int ram, @PathVariable("capacity") int capacity, @PathVariable("productId") int productId) {
        List<String> colors = productInfoService.getColorByCapacity(ram, capacity, productId);
        return colors;
    }

    @GetMapping("/select-color/{ram}/{capacity}/{productId}")
    public ResponseEntity<ProductInfo> getProductInfo(
            @RequestParam("color") String color,
            @PathVariable("ram") int ram,
            @PathVariable("capacity") int capacity,
            @PathVariable("productId") int productId) {
        System.out.println("color:" + color);
        List<ProductInfo> productInfoList = productInfoService.getProductInfo(ram, capacity, productId);
        for (ProductInfo product : productInfoList) {
            if (product.getColor().equalsIgnoreCase(color)) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }
        @PostMapping("/addToCart/{quantity}/{productInfoId}")
    public Boolean addToCart(@PathVariable("quantity") int quantity, @PathVariable("productInfoId") int productInfoId) {
        HttpSession session = httpSession;
        if(session.getAttribute("id") == null) {
            return false;
        } else {
            String customerId = session.getAttribute("id").toString();
            Cart cart = new Cart();
            // Khởi tạo đối tượng Customer và ProductInfo
            Customer customer = new Customer();
            customer.setCustomerId(Integer.parseInt(customerId));
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductInfoId(productInfoId);
            // Thiết lập các giá trị cho đối tượng Cart
            cart.setCustomer(customer);
            cart.setQuantity(quantity);
            cart.setProductInfo(productInfo);
            System.out.println(productInfo);
            List<Cart> cartList = cartRepository.findAll();
            for(Cart cartX :cartList ){
                if(cartX.getCustomer().getCustomerId() == Integer.parseInt(customerId) && cartX.getProductInfo().getProductInfoId()== productInfoId){
                    cart.setCartId(cartX.getCartId());
                    cart.setQuantity(quantity + cartX.getQuantity());
                }
            }
            // Lưu đối tượng Cart vào cơ sở dữ liệu
            cartRepository.save(cart);
            return true;
        }
    }
}