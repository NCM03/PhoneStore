package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;

import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.CustomerService;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    JwtUtils jwtUtils =new JwtUtils();
    HelperToken helperToken;
    @GetMapping("/Customer/Profile")
    public String getProfile(Model model, HttpServletRequest request) {
try {


    String token = helperToken.getToken(request);
    if (token == null) {
        return "redirect:/Login";
    }
    Account account = jwtUtils.decodeToken(token);

    if (account != null) {
        Customer customer = customerService.getCustomer(account);
        model.addAttribute("customer", customer);
        return "Profile";
    } else {
        return "redirect:/Login";
    }
} catch (Exception e){
    return "redirect:/Login";
}
    }

    @PostMapping("/Customer/UpdateProfile")
    public String updateProfile(@Valid Customer customer, BindingResult result, RedirectAttributes redirectAttributes) {
        {
            try {
                if (result.hasErrors()) {
                    return "Profile";
                } else {

                    customerService.saveCustomer(customer);

                    redirectAttributes.addFlashAttribute("successFullMessage", "Update profile successfully!");
                    return "redirect:/Customer/Profile";
                }
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Update profile failed!");
                return "redirect:/Customer/Profile";
            }
        }
    }
    }


