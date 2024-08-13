package fa.training.phonestore.controller;


import fa.training.phonestore.entity.*;
import fa.training.phonestore.service.*;
import fa.training.phonestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.Console;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryRepository categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    AccountService accountService;
    @Autowired
    BrandService brandService;


    @GetMapping({"/index","/"})
    public String home(@RequestParam(defaultValue = "0", value = "page") int page,
                       @RequestParam(defaultValue = "8", value = "size") int size,
                       Model model) {
        List<Brand> brands = brandService.findAll();
        Page<Product> productsFeature = productService.findAll(PageRequest.of(page,size));
        List<ProductInfo> lastestProduct = productInfoService.getHomeLastestProduct();
        Page<Product> reviewProduct = productService.getHomeReviewProduct();
        List<Product> RatingProduct = productService.getHomeTopRateProduct();
        model.addAttribute("list", brands);
        model.addAttribute("feature", productsFeature);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastestProduct", lastestProduct);
        model.addAttribute("reviewProduct", reviewProduct);
        model.addAttribute("ratingProduct1", RatingProduct);
        return "index";
    }

    @GetMapping({"/search/{keyword}"})
    public String search(@RequestParam(required = false, value = "keyword") String keyword,
                         @RequestParam(required = false, value = "capacity") Integer capacity,
                         @RequestParam(required = false, value = "ram") Integer ram,
                         @RequestParam(required = false, value = "pricemin") Integer pricemin,
                         @RequestParam(required = false, value = "pricemax") Integer pricemax,
                         @RequestParam(required = false, value = "brandID") Integer brandID,
                         @RequestParam(defaultValue = "0", value = "page") int page,
                         @RequestParam(defaultValue = "6", value = "size") int size,
                         Model model) {
        List<Brand> brands = brandService.findAll();
        model.addAttribute("list", brands);
        List<ProductInfo> searchPage = productInfoService.getSearchProduct(keyword);
        Page<ProductInfo> searchProduct = productInfoService.findAllByProductInfoNameAndCapacityAndRamAndPrice(keyword,capacity,ram,pricemin,pricemax,brandID,page,size);
        List<ProductInfo> discount = productInfoService.getProductInfoDiscount();
        model.addAttribute("discount", discount);
        model.addAttribute("currentPages", page);
        model.addAttribute("keys", keyword);
        model.addAttribute("productAll", searchProduct);
        model.addAttribute("searchPage", searchPage);
        return "productsearch";
    }

    @GetMapping({"/contact"})
    public String contact(Model model) {
        List<Brand> brands = brandService.findAll();
        model.addAttribute("list", brands);
        return "contact";
    }

    @GetMapping({"/shop-grid"})
    public String shop(@RequestParam(required = false, value = "keyword") String keyword,
                       @RequestParam(required = false, value = "capacity") Integer capacity,
                       @RequestParam(required = false, value = "ram") Integer ram,
                       @RequestParam(required = false, value = "pricemin") Integer pricemin,
                       @RequestParam(required = false, value = "pricemax") Integer pricemax,
                       @RequestParam(required = false, value = "brandID") Integer brandID,
                       @RequestParam(defaultValue = "0", value = "pages") int page,
                       @RequestParam(defaultValue = "9", value = "size") int size,
                       Model model) {
        List<Brand> brands = brandService.findAll();
        Page<ProductInfo> product = productInfoService.findAllByProductInfoNameAndCapacityAndRamAndPrice(keyword,capacity,ram,pricemin,pricemax,brandID,page,size);
        List<ProductInfo> discount = productInfoService.getProductInfoDiscount();
        model.addAttribute("discount", discount);
        model.addAttribute("list", brands);
        model.addAttribute("productAll", product);
        model.addAttribute("currentPages", page);
        return "shop-grid";
    }

    @GetMapping ({"/Brand/id"})
    public String searchCategory(@PathVariable(value = "id") int categoryID ,
                                 @RequestParam(required = false,defaultValue = "0", value = "page") int page,
                                 @RequestParam(required = false,defaultValue = "9", value = "size") int size,
                                 Model model) {
        List<Category> categories = categoryService.findAll();
        Page<Product> searchProduct = productService.findByCategory(categoryID,page,size);
        model.addAttribute("searchPage", searchProduct);
        model.addAttribute("list", categories);
        model.addAttribute("searchProduct", searchProduct);
        return "productsearch";
    }
}
