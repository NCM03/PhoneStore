package fa.training.phonestore.controller;


import fa.training.phonestore.entity.*;
import fa.training.phonestore.service.*;
import fa.training.phonestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryRepository categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSupportService productSupportService;

    @GetMapping({"/","/index"})
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> products = productRepository.findAll();
        model.addAttribute("list", categories);
        model.addAttribute("products", products);
        for (Product product : products) {
            Page<ProductSupport> productsfeature = productSupportService.findProductSupportByProductId(product.getProductId());
            model.addAttribute("feature", productsfeature);
        }
        Page<ProductSupport> lastestProduct = productSupportService.getHomeLastestProduct();
        Page<Product> reviewProduct = productService.getHomeReviewProduct();
        Page<Product> RatingProduct = productService.getHomeTopRateProduct();
        model.addAttribute("lastestProduct", lastestProduct);
        model.addAttribute("reviewProduct", reviewProduct);
        model.addAttribute("ratingProduct", RatingProduct);
        return "index";
    }


}
