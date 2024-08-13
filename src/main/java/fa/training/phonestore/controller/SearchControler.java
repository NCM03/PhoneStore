package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Category;
import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.repository.CategoryRepository;
import fa.training.phonestore.service.CategoryService;
import fa.training.phonestore.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("")
public class SearchControler {
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    CategoryRepository categoryRepository;

//    @GetMapping("/filter")
//    public Page<ProductInfo> filter(@RequestParam int page, @RequestParam int size) {
//
//    }
}
