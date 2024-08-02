package fa.training.phonestore.controller;


import fa.training.phonestore.entity.*;
import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.*;
import fa.training.phonestore.repository.*;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


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

    @Autowired
    private ProductSupportRepository productSupportRepository;
    JwtUtils jwtUtils = new JwtUtils();
    HelperToken helperToken;

    @GetMapping({"/", "/index"})
    public String home(Model model, HttpServletRequest request) {
        try {


            String token = helperToken.getToken(request);
            if (token == null) {
                List<Category> categories = categoryService.findAll();
                List<Product> products = productRepository.findAll();
                model.addAttribute("list", categories);
                model.addAttribute("products", products);
                for (Product product : products) {
                    Page<ProductSupport> productsfeature = productSupportService.findProductSupportByProductId(product.getProductId());
                    model.addAttribute("feature", productsfeature);
                }
                List<ProductSupport> lastestProduct = productSupportService.getHomeLastestProduct();
                Page<Product> reviewProduct = productService.getHomeReviewProduct();
                List<Product> RatingProduct1 = productService.getHomeTopRateProductSection1();
                model.addAttribute("lastestProduct", lastestProduct);
                model.addAttribute("reviewProduct", reviewProduct);
                model.addAttribute("ratingProduct1", RatingProduct1);
                return "index";
            }
            Account account = jwtUtils.decodeToken(token);

            List<Category> categories = categoryService.findAll();
            List<Product> products = productRepository.findAll();
            model.addAttribute("list", categories);
            model.addAttribute("products", products);
            for (Product product : products) {
                Page<ProductSupport> productsfeature = productSupportService.findProductSupportByProductId(product.getProductId());
                model.addAttribute("feature", productsfeature);
            }
            List<ProductSupport> lastestProduct = productSupportService.getHomeLastestProduct();
            Page<Product> reviewProduct = productService.getHomeReviewProduct();
            List<Product> RatingProduct1 = productService.getHomeTopRateProductSection1();
            model.addAttribute("lastestProduct", lastestProduct);
            model.addAttribute("reviewProduct", reviewProduct);
            model.addAttribute("ratingProduct1", RatingProduct1);
            model.addAttribute("acc", account);
            return "index";
        } catch (Exception e) {
            return "index";
        }
    }

    @GetMapping({"/search/{keyword}"})
    public String search(@Param(value = "keyword") String keyword, Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> product = productRepository.findAll();
        model.addAttribute("list", categories);
        model.addAttribute("productAll", product);
        Page<ProductSupport> searchPage = productSupportService.getSearchProduct(keyword, 0, 6);
        List<ProductSupport> searchProduct = productSupportRepository.findAll();
        model.addAttribute("searchProduct", searchProduct);
        model.addAttribute("searchPage", searchPage);
        return "productsearch";
    }

}
