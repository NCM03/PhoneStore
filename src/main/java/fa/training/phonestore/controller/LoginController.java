package fa.training.phonestore.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.DTO;
import fa.training.phonestore.sercurity.SecurityConstraints;
import fa.training.phonestore.service.imp.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

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
    SecurityConstraints securityConstraints = new SecurityConstraints();
    @GetMapping("/Logout123")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Xóa token JWT khỏi cookie nếu có
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("remember-me-token".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        // Xóa session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Chuyển hướng đến trang đăng nhập hoặc trang chủ
        return "redirect:/Login";
    }

    @GetMapping("/Login")
    public String getlogin(Model m) {
        DTO dto= new DTO();
        m.addAttribute("dto",dto);
        Account account = new Account();
        m.addAttribute("acc",account);
        return "Login";
    }

    @PostMapping("/Login")
    public ModelAndView submitGrade(@Valid @ModelAttribute("dto") @RequestBody DTO dto,
                                    BindingResult result,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return new ModelAndView("Login");
            }
            Account account = accountService.searchUser(dto.getUsername());
            if (account != null) {
                if (!bCryptPasswordEncoder.matches(dto.getPassword(), account.getPassword())) {

                    result.rejectValue("password", "error.user", "Password is invalid. Try Again");
                    return new ModelAndView("Login");
                } else {
                    if (account.isActive() == false) {
                        redirectAttributes.addFlashAttribute("FailedFullMessage", "Your account is block.Please Contact to admin");
                        return new ModelAndView("redirect:/Login");

                    }
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<DTO> httpRequest = new HttpEntity<>(dto, headers);

                    try {
                        ResponseEntity<String> authResponse = restTemplate.postForEntity(securityConstraints.Authenticate, httpRequest, String.class);
                        String responseBody = authResponse.getBody();
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode root = mapper.readTree(responseBody);
                        String token = root.path("token").asText();
                        token = token.replace(SecurityConstraints.BEARER, "");
                        String role = root.path("role").asText();


                        // Xử lý Remember Me
                        boolean rememberMe = "on".equals(request.getParameter("remember-me"));
                            // Tạo cookie với token JWT
                            Cookie rememberMeCookie = new Cookie("remember-me-token", token);
                            rememberMeCookie.setMaxAge(7 * 24 * 60 * 60); // Cookie tồn tại 7 ngày
                            rememberMeCookie.setPath("/");
                            rememberMeCookie.setHttpOnly(true); // Tăng cường bảo mật
                            response.addCookie(rememberMeCookie);

                        if ("admin".equals(role)) {
                            return new ModelAndView("redirect:/Account/Admin");
                        } else if ("customer".equals(role)) {
                            return new ModelAndView("redirect:/index");
                        } else {
                            return new ModelAndView("redirect:/Employee/Home");
                        }

                    } catch (Exception e) {
                        e.printStackTrace(); // In ra stack trace để debug
                        result.rejectValue("username", "error.user", "An error occurred during authentication");
                        return new ModelAndView("Login");
                    }
                }
            } else {
                result.rejectValue("username", "error.user", "Username is invalid");
                return new ModelAndView("Login");
            }
        }catch (Exception e){
            result.rejectValue("username", "error.user", "Failed, Please try again");
            return new ModelAndView("Login");
        }
    }
    }


