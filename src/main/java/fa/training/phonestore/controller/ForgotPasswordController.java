package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.DTO;
import fa.training.phonestore.helper.HelperPassword;
import fa.training.phonestore.service.imp.AccountService;
import fa.training.phonestore.service.imp.CustomerService;
import fa.training.phonestore.service.EmailService;
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
    @Autowired
    private EmailService emailService;

    HelperPassword helperPassword = new HelperPassword();

    @GetMapping("/forgotpassword")
    public String forgotPassword() {
        return "ForgetPassword";
    }

    @PostMapping("/getPassword")
    public String checkEmailOrPhone(String email, String accountId, RedirectAttributes redirectAttributes, Model m) {
        try {


            if (email == null || accountId == null) {

                return "redirect:/ValidAuthenticate";
            }
            Customer customer = customerService.getCustomerByEmail(email);
            Account account = accountService.seachAccountById(Integer.parseInt(accountId));
            if (customer.getAccount().getAccountId() != account.getAccountId()) {
                redirectAttributes.addFlashAttribute("error", "Email or AccountId is incorrect");
                return "redirect:/forgotpassword";
            } else if(customer==null||account==null){
                redirectAttributes.addFlashAttribute("error", "Email is not exist. Please try again or contact admin for support");
                return "redirect:/forgotpassword";

            }else{
                String newPassword = helperPassword.generateRandomPassword();
                account.setPassword(newPassword);
                accountService.save(account);
                emailService.sendPasswordResetEmail(customer.getEmail(), newPassword);
                redirectAttributes.addFlashAttribute("successFullMessage", "Password has been sent to your email");
                return "redirect:/Login";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed");
            return "redirect:/forgotpassword";
        }
    }

    @PostMapping("/GetBackPass")
    public String GetPassword(@Valid @ModelAttribute("dto") DTO dto, BindingResult result, RedirectAttributes redirectAttributes) {
        try {


            if (result.hasErrors()) {
                return "fali";
            }
            Account account = accountService.searchUser(dto.getUsername());
            if (account == null) {
                result.addError(new FieldError("dto", "username", "Username does not exist"));
                return "fali";
            }
            account.setUsername(dto.getUsername());
            account.setPassword(dto.getPassword());
            accountService.save(account);
            redirectAttributes.addFlashAttribute("successFullMessage", "Change Password Successful");
            return "redirect:/Login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("successFullMessage", "Failed, please try again");
            return "redirect:/Login";
        }
    }
}




