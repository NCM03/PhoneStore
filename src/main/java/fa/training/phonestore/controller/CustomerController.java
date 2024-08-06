package fa.training.phonestore.controller;

import fa.training.phonestore.entity.*;

import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.imp.CustomerService;
import fa.training.phonestore.service.imp.EmployeeService;
import fa.training.phonestore.service.imp.ImageRequestService;
import fa.training.phonestore.service.imp.RequestEntityService;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class CustomerController {
    @Autowired
    RequestEntityService requestEntityService;
    @Autowired
    ImageRequestService imageRequestService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;
    JwtUtils jwtUtils = new JwtUtils();
    HelperToken helperToken;

    @GetMapping("/Customer/Profile")
    public String getProfile(Model model, HttpServletRequest request) {
        try {


            String token = helperToken.getToken(request);
            if (token == null) {
                return "redirect:/Login";
            }
            Account account = jwtUtils.decodeToken(token);

            if (account != null) {
                Customer customer = customerService.getCustomer(account);
                model.addAttribute("customer", customer);
                return "Profile";
            } else {
                return "redirect:/Login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Login";
        }
    }

    @PostMapping("/Customer/UpdateProfile")
    public String updateProfile(@Valid Customer customer, BindingResult result, RedirectAttributes redirectAttributes) {
        {
            boolean emailExists = customerService.existsByEmail(customer.getEmail());

            try {
                if (result.hasErrors()) {
                    return "Profile";
                }


                if (emailExists) {
                    result.rejectValue("email", "error.email", "Email already exists");
                    return "Profile";

                } else {

                    customerService.saveCustomer(customer);

                    redirectAttributes.addFlashAttribute("successFullMessage", "Update profile successfully!");
                    return "redirect:/Customer/Profile";
                }
            } catch (Exception e) {
              e.printStackTrace();
                redirectAttributes.addFlashAttribute("errorMessage", "Update profile failed!");
                return "redirect:/Customer/Profile";
            }
        }
    }
    @PostMapping("Customer/RequestDetail")
    public String goToRequestDetail (@RequestParam int requestID, HttpServletRequest request, Model model){
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        List<ImageRequest> imageRequest = imageRequestService.findByRequestID(requestID);

        RequestEntity requestEntity = requestEntityService.findByRequestID(requestID);
        Customer customer= customerService.getCustomerByCustomerID(requestEntity.getCustomerID());
        model.addAttribute("requestEntity",requestEntity);
        model.addAttribute("imageRequest",imageRequest);
        model.addAttribute("customer",customer);
        if(requestEntity.getStatus()==0||requestEntity.getStatus()==2){
            Employee employee=employeeService.getEmployeeByEmployeeID(requestEntity.getEmployeeID());
            model.addAttribute("employee",employee);
        }
        model.addAttribute("acc",account);
        return "DetailRequestForCustomer";
    }




}


