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
@RequestMapping("/search/")
public class SearchControler {
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    CategoryRepository categoryRepository;

//    @PostMapping({"/{keyword}"})
//    public ModelAndView search(Model model,
//                               @Param(value = "keyword") String keyword ,
//                               @RequestParam(value = "page", required = false) int page,
//                               @RequestParam(value = "size", required = false) int size){
//        try{
//            List<Category> categories = categoryRepository.findAll();
//            model.addAttribute("list", categories);
//            Page<ProductInfo> searchProduct = productInfoService.getSearchList(keyword,page,size);
//            if(page > 0){
//                model.addAttribute("searchProduct", searchProduct);
//                return new ModelAndView("productsearch");
//            }
//        }
//        catch (Exception e){
//            return new ModelAndView("403");
//        }
//        return new ModelAndView("403");
//    }
}
