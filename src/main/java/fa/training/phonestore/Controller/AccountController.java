package fa.training.phonestore.Controller;

import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Entity.DTO;
import fa.training.phonestore.Service.AccountService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/Account")
public class AccountController {
    @Autowired
    RestTemplate restTemplate ;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AccountService accountService;
    @PostMapping("/save")
public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<List<Account>> getAllAccount(HttpSession session) {
        session.getAttribute("token");
        return new ResponseEntity<>(accountService.findAll(),HttpStatus.OK);
    }
    @ResponseBody
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody DTO dto,HttpSession session) {
        String url = "http://localhost:2612/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DTO> request = new HttpEntity<>(dto, headers);

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, request, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            HttpHeaders responseHeaders = response.getHeaders();
            String fullToken = responseHeaders.getFirst(HttpHeaders.AUTHORIZATION);
            session.setAttribute("token", fullToken);


            // Trả về token trong response body
            return ResponseEntity.ok().body(Collections.singletonMap("token", fullToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Đăng nhập thất bại");
        }
    }
    @GetMapping("/Login")
    public String getlogin(Model m) {
        DTO acc= new DTO();
        m.addAttribute("account",acc);
        return "Login";
    }


}