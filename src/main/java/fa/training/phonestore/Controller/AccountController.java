package fa.training.phonestore.Controller;

import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Entity.DTO;
import fa.training.phonestore.Exception.EntityNotFoundException;
import fa.training.phonestore.Service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Account")
public class AccountController {
    @Autowired
    RestTemplate restTemplate ;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AccountService accountService;
    @GetMapping("/Admin")
public String homeAdmin() {
        return "HomeAdmin";
    }

    @GetMapping("/GetAllAccount")
    public String getAllAccount(Model m) {
        List<Account> accountList=accountService.findAll();
        m.addAttribute("accountList", accountList);
        return "AllAccount";
    }
    @PostMapping("/checkUsername")
    public ResponseEntity<Boolean> checkUser(@RequestParam String username) {

           Account account= accountService.searchUser(username);
            if(account!=null) {
                return ResponseEntity.ok(true); // Username exists
            }else{
            return ResponseEntity.ok(false); // Username does not exist
        }
    }
        @PostMapping("/Register")
        public String registerAccount(DTO dto,RedirectAttributes redirectAttributes){
            Account account = new Account();
            account.setPassword(dto.getPassword());
            account.setUsername(dto.getUsername());
            account=accountService.save(account);
            redirectAttributes.addFlashAttribute("successFullMessage", "Register Successful");
            return "redirect:/Login";
        }
        // CHange Password
        @GetMapping("/ChangePassword")
        public String getChangePassword(Model m){
            DTO dto= new DTO();
            m.addAttribute("dto",dto);
            return "ChangePassword";
        }
        @PostMapping("/changePassword")
    public ModelAndView changePassword(@Valid @ModelAttribute("dto") DTO dto, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model){
            HttpSession session = request.getSession(true);


        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("ChangePassword");
            return mav;
        }
            Account account = (Account) session.getAttribute("account");
            dto.setUsername(account.getUsername());
        Account account1 = accountService.searchUser(dto.getUsername());
        if(account==null){
            result.addError(new FieldError("dto","username","Username does not exist"));
            return new ModelAndView("ChangePassword");
        }
        account1.setPassword(dto.getPassword());
        accountService.save(account1);
        redirectAttributes.addFlashAttribute("successFullMessage", "Change Password Successful");
        return new ModelAndView("redirect:/Login");
    }

}