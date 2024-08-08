package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Cart;
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
public class ProductAPI {

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

    @Qualifier("productInfoService")

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}