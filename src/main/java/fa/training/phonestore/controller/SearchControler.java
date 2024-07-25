package fa.training.phonestore.controller;

import fa.training.phonestore.entity.ProductSupport;
import fa.training.phonestore.service.ProductSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/search/{keyword}")
//public class SearchControler {
//    @Autowired
//    ProductSupportService productSupportService;
//
//
//    @PostMapping
//    public Page<ProductSupport> search(@PathVariable String keyword) {
//
//        return productSupportService.getSearchProduct(keyword,0,3);
//    }
//}
