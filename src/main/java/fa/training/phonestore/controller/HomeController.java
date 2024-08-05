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
    private ProductInfoService productInfoService;
    @Autowired
    AccountService accountService;

    @GetMapping({"/index","/"})
    public String home(@RequestParam(defaultValue = "0", value = "page") int page,
                       @RequestParam(defaultValue = "8", value = "size") int size,
                       Model model) {
        List<Category> categories = categoryService.findAll();
        Page<ProductInfo> productsFeature = productInfoService.findAll(PageRequest.of(page,size));
        List<ProductInfo> lastestProduct = productInfoService.getHomeLastestProduct();
        Page<Product> reviewProduct = productService.getHomeReviewProduct();
        List<Product> RatingProduct1 = productService.getHomeTopRateProductSection1();
        model.addAttribute("list", categories);
        model.addAttribute("feature", productsFeature);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastestProduct", lastestProduct);
        model.addAttribute("reviewProduct", reviewProduct);
        model.addAttribute("ratingProduct1", RatingProduct1);
        return "index";
    }

    @GetMapping({"/search/{keyword}"})
    public String search(@Param(value = "keyword") String keyword ,
                         @RequestParam(defaultValue = "0", value = "page") int page,
                         @RequestParam(defaultValue = "6", value = "size") int size,
                         Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> product = productRepository.findAll();
        model.addAttribute("list", categories);
        model.addAttribute("productAll", product);
        List<ProductInfo> searchPage = productInfoService.getSearchProduct(keyword);
        Page<ProductInfo> searchProduct = productInfoService.getSearchList(keyword, page, size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchProduct", searchProduct);
        model.addAttribute("searchPage", searchPage);
        return "productsearch";
    }

    @PostMapping({"/Category/{id}"})
    public String searchCategory(@RequestParam(value = "id") int categoryID ,
                                 @RequestParam(defaultValue = "0", value = "page") int page,
                                 @RequestParam(defaultValue = "6", value = "size") int size,
                                 Model model) {
        List<Category> categories = categoryService.findAll();
        Page<Product> searchProduct = productService.findByCategory(categoryID,page,size);
        model.addAttribute("searchPage", searchProduct);
        model.addAttribute("list", categories);
        model.addAttribute("searchProduct", searchProduct);
        return "productsearch";
    }

//    @PostMapping({"/search/{keyword}"})
//    public String search(RedirectAttributes redirectAttributes,
//                                 Model model,
//                                 @Param(value = "keyword") String keyword ,
//                                 @RequestParam(value = "page", required = false) int page,
//                                 @RequestParam(value = "size", required = false) int size){
//        try{
//            List<Category> categories = categoryService.findAll();
//            model.addAttribute("list", categories);
//            Page<ProductInfo> searchProduct = productInfoService.getSearchList(keyword,page,size);
//            if(page > 0){
//                model.addAttribute("searchProduct", searchProduct);
//                return "productsearch";
//            }
//        }
//        catch (Exception e){
//            return null;
//        }
//        return null;
//    }
}
