package fa.training.phonestore.Controller;

import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Entity.Customer;

import fa.training.phonestore.Service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/Customer/Profile")
    public String getProfile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            Customer customer = customerService.getCustomer(account);
            model.addAttribute("customer", customer);
            return "Success";
        } else {
            return "Success";
        }
    }

    @PostMapping("/Customer/UpdateProfile")
    public String updateProfile(@Valid Customer customer, BindingResult result, RedirectAttributes redirectAttributes) {
        {
            if (result.hasErrors()) {
                return "Success";
            } else {
                customerService.saveCustomer(customer);

                redirectAttributes.addFlashAttribute("successFullMessage", "Update profile successfully!");
                return "redirect:/Customer/Profile";
            }
        }
    }
}

