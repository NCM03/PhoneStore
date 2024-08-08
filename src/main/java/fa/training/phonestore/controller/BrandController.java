package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Brand;
import fa.training.phonestore.service.imp.BrandService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Brand")
public class BrandController {
    @Autowired private BrandService brandService;

    @PostMapping("/deleteBrand")
    public String deleteBrand(@ModelAttribute("brand") Brand brand){
        brandService.deleteBrandByBrandID(brand.getBrandId());
        return "ManageBrandForAdmin";
    }
}
