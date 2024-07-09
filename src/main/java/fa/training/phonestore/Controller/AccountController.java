package fa.training.phonestore.Controller;

import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Account")
public class AccountController {
    @Autowired
    AccountService accountService;
@GetMapping("/getAll")
    public String getAllAccount(Model model) {
    List<Account> accountList = accountService.findAll();
    model.addAttribute("accountList",accountList);
       return "HelloWorld";
    }
    @GetMapping("/Login")
    public String login(Model m) {
    Account account = new Account();
    m.addAttribute("account",account);
    return "Login";


    }
}