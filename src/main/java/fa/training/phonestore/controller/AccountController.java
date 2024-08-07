package fa.training.phonestore.controller;


import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.DTO;

import fa.training.phonestore.entity.Employee;
import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.*;

import fa.training.phonestore.service.imp.*;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RoleService roleService;
    @Autowired
    RequestEntityService requestEntityService;
    @Autowired
            InvoiceService invoiceService;
    JwtUtils jwtUtils =new JwtUtils();
    HelperToken helperToken;
    @GetMapping("/Admin")
    public String homeAdmin(Model model) {
        long countRequest = requestEntityService.count();
        long countInvoice = invoiceService.countAll();
        long countRequestStatus2 = requestEntityService.countByStatus(2);
        long countRequestStatus1 = requestEntityService.countByStatus(1);
        long countRequestStatus0 = requestEntityService.countByStatus(0);
        model.addAttribute("countRequest", countRequest);
        model.addAttribute("countInvoice", countInvoice);
        model.addAttribute("countRequestStatus2", countRequestStatus2);
        model.addAttribute("countRequestStatus1", countRequestStatus1);
        model.addAttribute("countRequestStatus0", countRequestStatus0);
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
            Account account= new Account();
            // Add attributes to model
            m.addAttribute("accountList", accountPage.getContent());
            m.addAttribute("currentPage", page);
            m.addAttribute("totalPages", accountPage.getTotalPages());
            m.addAttribute("totalItems", accountPage.getTotalElements());
            m.addAttribute("size", size);
            m.addAttribute("searchTerm", searchTerm);
            m.addAttribute("sortField", sortField);
            m.addAttribute("account", account);
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
    @ResponseBody
    public ResponseEntity<Map<String, String>> registerAccount(@Valid @ModelAttribute("dto") DTO dto, BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(fieldError -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors); // Trả về lỗi dưới dạng JSON
        }

        try {
            Account account = new Account();
            account.setPassword(dto.getPassword());
            account.setUsername(dto.getUsername());
            account.setRole(roleService.getRoleById(2));
            account.setActive(true);
            account = accountService.save(account);

            return ResponseEntity.ok(Collections.singletonMap("success", "Register Successful")); // Thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed, Please try again")); // Lỗi hệ thống
        }
    }

    // CHange Password
    @GetMapping("/ChangePassword")
    public String getChangePassword(Model m) {
        DTO dto = new DTO();
        m.addAttribute("dto", dto);
        return "ChangePassword";
    }

    @PostMapping("/changePassword1")
    public ModelAndView changePassword(@Valid @ModelAttribute("dto") DTO dto,

                                       BindingResult result,
                                       @RequestParam String useaaa,
                                       RedirectAttributes redirectAttributes,
                                       HttpServletRequest request,
                                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return new ModelAndView("ChangePassword");
        }

        try {
            String token = helperToken.getToken(request);
            if (token == null) {
                return new ModelAndView("redirect:/Login");
            }

            Account account = jwtUtils.decodeToken(token);
            if (account == null) {
                return new ModelAndView("redirect:/Login");
            }

            if (!bCryptPasswordEncoder.matches(useaaa, account.getPassword())) {
                redirectAttributes.addFlashAttribute("Erro", "Password không đúng.");
                return new ModelAndView("redirect:/Account/ChangePassword");
            }

            account.setPassword(dto.getPassword());
        accountService.save(account);

            redirectAttributes.addFlashAttribute("successFullMessage", "Password changed successfully!");
            return new ModelAndView("redirect:/Account/ChangePassword");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to change password.");
            return new ModelAndView("redirect:/Login");
        }
    }

    @PostMapping("/CreateAccount")
    public ModelAndView createAccount(@Valid @ModelAttribute("account") Account account, BindingResult result, RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return new ModelAndView("AdminManageAccount");
            }
            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
            account.setRole(roleService.getRoleById(account.getRole().getRoleId()));
            accountService.save(account);
            redirectAttributes.addFlashAttribute("successFullMessage", "Create Account Successful");
            return new ModelAndView("redirect:/Account/GetAllAccount");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("successFullMessage", "Failed, Please try again");
            return new ModelAndView("redirect:/Account/GetAllAccount");
        }
    }
    @PostMapping("/GetDetails")
    @ResponseBody
    public ResponseEntity<?> getAccountDetails(@RequestParam int accountId, @RequestParam int roleId) {
        try {
            if(roleId==2) {
                Account account = accountService.seachAccountById(accountId);
                Customer customer = customerService.getCustomer(account);
                return ResponseEntity.ok(customer);
            }else if(roleId==3){
                Account account = accountService.seachAccountById(accountId);
                Employee employee= employeeService.getEmployeeByAccount(account);
                return ResponseEntity.ok(employee);
            }else {

                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
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