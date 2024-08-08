package fa.training.phonestore.controller;

import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.service.ProductServiceImp;
import fa.training.phonestore.service.imp.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping("/api")
public class ProductInfoAPI {
    @Autowired
    private ProductInfoService productInfoService;
    @GetMapping("/productInfos/{id}")

    public ResponseEntity<Optional<ProductInfo>> getProductInfoById(@PathVariable("id") int id) {
        Optional<ProductInfo> productInfoOptional = productInfoService.findById(id);
        if (productInfoOptional != null) {
            return ResponseEntity.ok(productInfoOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}