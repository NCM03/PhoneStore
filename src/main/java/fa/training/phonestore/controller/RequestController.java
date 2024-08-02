package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.RequestEntity;
import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.imp.CustomerService;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {
    @Autowired
    CustomerService customerService;
    JwtUtils jwtUtils = new JwtUtils();
    HelperToken helperToken;
    @GetMapping("/Customer/Application")
public String getRequest(Model m, HttpServletRequest request){
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
    RequestEntity requestEntity = new RequestEntity();
        Customer customer = customerService.getCustomer(account);
    m.addAttribute("requestEntity",requestEntity);
    m.addAttribute("acc",account);
    m.addAttribute("customer",customer);
return "ApplicationForCustomer";
}

}
