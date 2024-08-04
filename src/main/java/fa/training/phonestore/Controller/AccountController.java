package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.DTO;
import fa.training.phonestore.exception.EntityNotFoundException;
import fa.training.phonestore.service.AccountService;

import fa.training.phonestore.service.CustomerService;
import fa.training.phonestore.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
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

import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/Account")
public class AccountController {
    @Autowired
    CustomerService customerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    RestTemplate restTemplate;
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
        List<Account> accountList = accountService.findAll();
        m.addAttribute("accountList", accountList);
        return "AllAccount";
    }

    @PostMapping("/checkUsername")
    public ResponseEntity<Boolean> checkUser(@RequestParam String username) {

        Account account = accountService.searchUser(username);
        if (account != null) {
            return ResponseEntity.ok(true); // Username exists
        } else {
            return ResponseEntity.ok(false); // Username does not exist
        }
    }

    @PostMapping("/Register")
    public String registerAccount(DTO dto, RedirectAttributes redirectAttributes) {
        Account account = new Account();
        account.setPassword(dto.getPassword());
        account.setUsername(dto.getUsername());
        account = accountService.save(account);
        redirectAttributes.addFlashAttribute("successFullMessage", "Register Successful");
        return "redirect:/Login";
    }

    // CHange Password
    @GetMapping("/ChangePassword")
    public String getChangePassword(Model m) {
        DTO dto = new DTO();
        m.addAttribute("dto", dto);
        return "ChangePassword";
    }

    @PostMapping("/changePassword")
    public ModelAndView changePassword(@Valid @ModelAttribute("dto") DTO dto, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(true);


        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("ChangePassword");
            return mav;
        }
        Account account = (Account) session.getAttribute("account");
        dto.setUsername(account.getUsername());
        Account account1 = accountService.searchUser(dto.getUsername());
        if (account == null) {
            result.addError(new FieldError("dto", "username", "Username does not exist"));
            return new ModelAndView("ChangePassword");
        }
        account1.setPassword(dto.getPassword());
        accountService.save(account1);
        redirectAttributes.addFlashAttribute("successFullMessage", "Change Password Successful");
        return new ModelAndView("redirect:/Login");
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam int accountId) throws MessagingException, UnsupportedEncodingException {
        String newPassword = generateRandomPassword();
        Account account = accountService.seachAccountById(accountId);
        Customer customer = customerService.getCustomer(account);
        // Cập nhật mật khẩu mới trong cơ sở dữ liệu
        account.setPassword(newPassword);
        accountService.save(account);

        // Gửi email chứa mật khẩu mới
        emailService.sendPasswordResetEmail(customer.getEmail(), newPassword);
        System.out.println("Da gui mail");
        return "Mật khẩu mới đã được gửi đến email của bạn.";
    }

    private String generateRandomPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(3, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(3);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);

        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar);

        List<Character> pwdChars = new ArrayList<>();
        for (char c : combinedChars.toCharArray()) {
            pwdChars.add(c);
        }
        Collections.shuffle(pwdChars);

        StringBuilder password = new StringBuilder();
        for (Character c : pwdChars) {
            password.append(c);
        }

        return password.toString();
    }

    //CHange active account
    @PostMapping("/take-Activities")
    public String changeActive(@RequestParam int accountId, RedirectAttributes redirectAttributes) {
        Account account = accountService.seachAccountById(accountId);
        if (account.isActive() == false) {
            account.setActive(true);
        } else {
            account.setActive(false);
        }
        redirectAttributes.addFlashAttribute("successFullMessage", "Change Password Successful");
        return "redirect:/Account/GetAllAccount";
    }
}