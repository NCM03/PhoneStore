package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.service.ProductInfoService;
import fa.training.phonestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductAPI {

    @Autowired
    private ProductService productService;
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