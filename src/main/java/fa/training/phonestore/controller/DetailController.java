package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductImage;
import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.repository.ProductImagineRepository;
import fa.training.phonestore.repository.ProductRepository;
import fa.training.phonestore.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/")
@Controller
public class DetailController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ProductImagineRepository productImagineRepository;

    @RequestMapping("/detailProduct/{id}")
    public String home(Model model, @PathVariable("id") int id) {
        Product product = productRepository.findByProductId(id);
        List<ProductInfo> productInfo = productInfoRepository.findByProduct_ProductId(id);
        List<ProductImage> productImage = productImagineRepository.findProductImagesByProductID(id);
        model.addAttribute("Product", product);
        model.addAttribute("ProductInfo", productInfo);
        model.addAttribute("ProductImage", productImage);
        Set<Integer> uniqueCapacities = new HashSet<>();
        for (ProductInfo ProductInfo : productInfo) {
            uniqueCapacities.add(ProductInfo.getCapacity());
        }
        Set<String> uniqueColor = new HashSet<>();
        for (ProductInfo ProductInfo : productInfo) {
            uniqueColor.add(ProductInfo.getColor());
        }
        Set<Integer> uniqueRams = new HashSet<>();
        for (ProductInfo ProductInfo : productInfo) {
            uniqueRams.add(ProductInfo.getRam());
        }
        model.addAttribute("uniqueRams", uniqueRams);
        model.addAttribute("uniqueColors", uniqueColor);
        model.addAttribute("uniqueCapacities",uniqueCapacities);
        return "shop-details";
    }


}
