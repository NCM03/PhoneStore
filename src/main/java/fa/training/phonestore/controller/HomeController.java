package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Brand;
import fa.training.phonestore.repository.CategoryRepository;
import fa.training.phonestore.repository.ProductInfoRepository;
import fa.training.phonestore.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryRepository categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @GetMapping("/")
    public String home(Model model) {
        List<Brand> brand = brandService.findAll();
        model.addAttribute("list", brand);
        return "index";
    }
    @GetMapping("/shop-grid")
    public String shopDetails(Model model) {
        return "shop-grid";
    }
    @GetMapping("/shopping-cart")
    public String shopCart(Model model) {
        return "shoping-cart";
    }


}
