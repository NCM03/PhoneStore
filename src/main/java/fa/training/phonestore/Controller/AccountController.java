package fa.training.phonestore.Controller;


import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Entity.Customer;
import fa.training.phonestore.Entity.DTO;

import fa.training.phonestore.Helper.HelperToken;
import fa.training.phonestore.Service.AccountService;

import fa.training.phonestore.Service.CustomerService;
import fa.training.phonestore.Service.EmailService;
import fa.training.phonestore.Utils.JwtUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    JwtUtils jwtUtils =new JwtUtils();
    HelperToken helperToken;
    @GetMapping("/Admin")
    public String homeAdmin() {
        return "HomeAdmin";
    }

    @GetMapping("/GetAllAccount")
    public String getAllAccount(Model m,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(required = false) Integer selectedSize,
                                @RequestParam(required = false) String searchTerm,
                                @RequestParam(required = false) String sortField) {

        // Update size if selectedSize is provided
        try {


            if (selectedSize != null) {
                size = selectedSize;
            }

            // Create pageable request with sorting if sortField is provided
            Pageable pageable;
            if (sortField != null && !sortField.isEmpty()) {
                pageable = PageRequest.of(page, size, Sort.by(sortField));
            } else {
                pageable = PageRequest.of(page, size);
            }

            // Call service to get accounts based on search term and pageable request
            Page<Account> accountPage;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                accountPage = accountService.findAccountsByUsername(searchTerm, pageable);
            } else {
                accountPage = accountService.findAllAccounts(pageable);
            }

            // Add attributes to model
            m.addAttribute("accountList", accountPage.getContent());
            m.addAttribute("currentPage", page);
            m.addAttribute("totalPages", accountPage.getTotalPages());
            m.addAttribute("totalItems", accountPage.getTotalElements());
            m.addAttribute("size", size);
            m.addAttribute("searchTerm", searchTerm);
            m.addAttribute("sortField", sortField);

            return "AdminManageAccount";
        }catch (Exception e){
            return "AdminManageAccount";
        }
    }


    @PostMapping("/searchAccount")
    public String searchAccount(@RequestParam("searchTerm") String searchTerm) {
        // Redirect to GetAllAccount with search term
        return "redirect:/Account/GetAllAccount?searchTerm=" + searchTerm;
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
        try {
            Account account = new Account();
            account.setPassword(dto.getPassword());
            account.setUsername(dto.getUsername());
            account = accountService.save(account);
            redirectAttributes.addFlashAttribute("successFullMessage", "Register Successful");
            return "redirect:/Login";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("successFullMessage", "Failed, Please try again");
            return "redirect:/Login";
        }
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
      try {


          if (result.hasErrors()) {
              ModelAndView mav = new ModelAndView("ChangePassword");
              return mav;
          }
          String token = helperToken.getToken(request);
          if (token == null) {
              return new ModelAndView("redirect:/Login");
          }


          Account account = jwtUtils.decodeToken(token);
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
      } catch (Exception e) {
          redirectAttributes.addFlashAttribute("successFullMessage", "Failed");
          return new ModelAndView("redirect:/Login");
      }
    }

    @PostMapping("/reset-password")
    public ModelAndView resetPassword(@RequestParam int accountId,RedirectAttributes redirectAttributes) throws MessagingException, UnsupportedEncodingException {
        try {
        String newPassword = generateRandomPassword();
        Account account = accountService.seachAccountById(accountId);
        Customer customer = customerService.getCustomer(account);

            // Cập nhật mật khẩu mới trong cơ sở dữ liệu
            account.setPassword(newPassword);
            accountService.save(account);

            // Gửi email chứa mật khẩu mới
            emailService.sendPasswordResetEmail(customer.getEmail(), newPassword);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("successFullMessage", "Failed to send email. Please try again later.");
            return new ModelAndView("redirect:/Account/GetAllAccount");
        }
        redirectAttributes.addFlashAttribute("successFullMessage", "Reset Password Successful.Please check your email to get new password.");
        return new ModelAndView("redirect:/Account/GetAllAccount");
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
        try {


            Account account = accountService.seachAccountById(accountId);
            if (account.isActive() == false) {
                account.setActive(true);
            } else {
                account.setActive(false);
            }
            accountService.save(account);
            redirectAttributes.addFlashAttribute("successFullMessage", "Change Acitivies successfull");
            return "redirect:/Account/GetAllAccount";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("successFullMessage", "Failed please try again");
            return "redirect:/Account/GetAllAccount";
        }
    }
}