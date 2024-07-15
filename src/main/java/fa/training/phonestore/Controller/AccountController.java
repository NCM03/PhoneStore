package fa.training.phonestore.Controller;

import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Entity.DTO;
import fa.training.phonestore.Service.AccountService;

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




}