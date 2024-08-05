package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.DTO;
import fa.training.phonestore.service.AccountService;
import fa.training.phonestore.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class ForgotPasswordController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;

    @GetMapping("/forgotpassword")
    public String forgotPassword() {
        return "ForgetPassword";
    }

    @PostMapping("/getPassword")
    public String checkEmailOrPhone(String email, RedirectAttributes redirectAttributes, Model m) {
        try {


            if (email == null) {

                return "redirect:/ValidAuthenticate";
            }
            Customer customer = customerService.getCustomerByEmail(email);

            if (customer != null) {
                Account account = accountService.seachAccountById(customer.getAccount().getAccountId());
                DTO dto = new DTO();
                dto.setUsername(account.getUsername());
                dto.setPassword(account.getPassword());
                m.addAttribute("dto", dto);
                return "fali";
            } else {
                redirectAttributes.addFlashAttribute("error", "Email không tồn tại");
                return "redirect:/forgotpassword";
            }
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Failed");
            return "redirect:/forgotpassword";
        }
    }
    @PostMapping("/GetBackPass")
    public String GetPassword(@Valid @ModelAttribute("dto") DTO dto, BindingResult result, RedirectAttributes redirectAttributes){
      try {


        if (result.hasErrors()) {
            return "fali";
        }
        Account account = accountService.searchUser(dto.getUsername());
        if(account==null){
            result.addError(new FieldError("dto","username","Username does not exist"));
            return "fali";
        }
        account.setUsername(dto.getUsername());
        account.setPassword(dto.getPassword());
        accountService.save(account);
        redirectAttributes.addFlashAttribute("successFullMessage", "Change Password Successful");
        return "redirect:/Login";}
      catch (Exception e){
          redirectAttributes.addFlashAttribute("successFullMessage", "Failed, please try again");
          return "redirect:/Login";}
      }
    }




