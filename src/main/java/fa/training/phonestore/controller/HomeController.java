package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Category;
import fa.training.phonestore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryRepository categoryService;

    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("list", categories);
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
