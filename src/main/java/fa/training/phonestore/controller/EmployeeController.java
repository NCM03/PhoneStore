package fa.training.phonestore.controller;

import fa.training.phonestore.entity.*;

import fa.training.phonestore.helper.HelperToken;

import fa.training.phonestore.service.imp.*;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    RequestEntityService requestEntityService;
    @Autowired
    ImageRequestService imageRequestService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    InvoiceService invoiceService;
    JwtUtils jwtUtils = new JwtUtils();
    HelperToken helperToken;
    @GetMapping("/Home")
    public String gotoHomeEmployee (Model model, HttpServletRequest request){
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
       Employee employee= employeeService.getEmployeeByAccount(account);
       long sumOfInvoice=invoiceService.countAll();
         int sumOfInvoiceByEmployee=invoiceService.countAllByEmployeeID(employee.getEmployeeId());

        model.addAttribute("employee",employee);
        model.addAttribute("sumOfInvoice",sumOfInvoice);
        model.addAttribute("sumOfInvoiceByEmployee",sumOfInvoiceByEmployee);

        return "HomeEmployee";
    }

    @PostMapping("/RequestDetail")
    public String goToRequestDetail (@RequestParam int requestID, HttpServletRequest request,Model model){
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
        return "DetailRequest";
    }
    @PostMapping("/answerRequest")
    public String answerRequest (@RequestParam int requestID, @RequestParam int status, @RequestParam String answer, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){



        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Employee employee=employeeService.getEmployeeByAccount(account);
        RequestEntity requestEntity=requestEntityService.findByRequestID(requestID);
        requestEntity.setEmployeeID(employee.getEmployeeId());
        requestEntity.setAnswer(answer);
        requestEntity.setAnswerDate(LocalDateTime.now());
        requestEntity.setStatus(status);
        requestEntityService.save(requestEntity);
        redirectAttributes.addFlashAttribute("successFullMessage","Xử lí đơn thành công ");
        return "redirect:/Employee/Request";
    }
}
