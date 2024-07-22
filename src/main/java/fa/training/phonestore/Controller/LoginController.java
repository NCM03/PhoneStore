package fa.training.phonestore.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fa.training.phonestore.entity.Account;
import fa.training.phonestore.Entity.DTO;
import fa.training.phonestore.sercurity.SecurityConstraints;
import fa.training.phonestore.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AccountService accountService;
    @Autowired
    RestTemplate restTemplate ;
    @Autowired
    private HttpSession httpSession;
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("jwtToken");
            session.invalidate();
        }
        return "redirect:/Login";
    }
    @GetMapping("/Login")
    public String getlogin(Model m) {
        DTO dto= new DTO();
        m.addAttribute("dto",dto);
        return "Login";
    }
    @PostMapping("/Login")
    public ModelAndView submitGrade(@Valid @ModelAttribute("dto") @RequestBody DTO dto, BindingResult result, HttpServletRequest request) {
        if(result.hasErrors()) {
            return new ModelAndView("Login");
        }
        Account account=new Account();
        account=accountService.searchUser(dto.getUsername());
        if(account!=null){
        if(!bCryptPasswordEncoder.matches(dto.getPassword().toString(), account.getPassword())){
            result.rejectValue("password", "error.user", "Password is invalid.Try Agian");
            return new ModelAndView("Login");
        }else{
            // Tạo một HttpEntity chứa DTO
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<DTO> httpRequest = new HttpEntity<>(dto, headers);

            try {
                // Sử dụng RestTemplate để gửi request POST đến /authenticate
                ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/authenticate", httpRequest, String.class);


                String responseBody = response.getBody();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseBody);
                String token = root.path("token").asText();
                String role = root.path("role").asText(); // Đọc vai trò từ token

                // Lưu token vào session
                HttpSession session = request.getSession(true);
                session.setAttribute("jwtToken", token);
                session.setAttribute("account", account);
                if ("admin".equals(role)) {

                    return new ModelAndView("redirect:/Account/Admin");
                } else if ("customer".equals(role)) {

                    return new ModelAndView("redirect:/Customer/Profile");
                } else {

                    return new ModelAndView("redirect:/default");
                }

            } catch (Exception e) {
                // Xử lý lỗi kết nối hoặc lỗi khác
                result.rejectValue("username", "error.user", "An error occurred during authentication");
                return new ModelAndView("Login");
            }}
        }else{
            result.rejectValue("username", "error.user", "Username is invalid");
            return new ModelAndView("Login");
        }

    }
    }


