package fa.training.phonestore.controller;

import fa.training.phonestore.entity.*;

import fa.training.phonestore.helper.HelperToken;

import fa.training.phonestore.service.imp.*;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    InvoiceItemService invoiceItemService;
    JwtUtils jwtUtils = new JwtUtils();
    HelperToken helperToken;
    @GetMapping("/Home")
    public String gotoHomeEmployee(Model model, HttpServletRequest request) {
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Employee employee = employeeService.getEmployeeByAccount(account);

        long sumOfRequest = requestEntityService.count();
        int sumOfRequestByEmployee = requestEntityService.countByEmployeeID(employee.getEmployeeId());
        model.addAttribute("employee", employee);
        model.addAttribute("sumOfRequest", sumOfRequest);
        model.addAttribute("sumOfRequestByEmployee", sumOfRequestByEmployee);

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
    @GetMapping("/ManageInvoice")
    public String getListBrand(Model m,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(required = false) Integer selectedSize,
                               @RequestParam(required = false) String searchTerm,
                               @RequestParam(defaultValue = "InvoiceDate") String sortField,
                               @RequestParam(defaultValue = "desc") String sortDir
            , HttpServletRequest request) {
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Employee employee = employeeService.getEmployeeByAccount(account);


        try {
            // Cập nhật kích thước trang nếu selectedSize được cung cấp
            if (selectedSize != null) {
                size = selectedSize;
            }

            // Tạo yêu cầu phân trang với sắp xếp
            Sort sort = Sort.by(Sort.Direction.DESC, sortField); // Mặc định là DESC
            if (sortDir.equalsIgnoreCase("asc")) {
                sort = Sort.by(Sort.Direction.ASC, sortField);
            }

            Pageable pageable = PageRequest.of(page, size, sort);

            Page<Invoice> invoicePage;
            if (searchTerm != null && !searchTerm.isEmpty()) {
               invoicePage= invoiceService.findByCustomerID(Integer.parseInt(searchTerm), pageable);
            } else {
               invoicePage = invoiceService.findByEmployeeID(employee.getEmployeeId(), pageable);
            }

            // Thêm thuộc tính vào model
            m.addAttribute("invoiceList", invoicePage.getContent());
            m.addAttribute("currentPage", page);
            m.addAttribute("totalPages", invoicePage.getTotalPages());
            m.addAttribute("totalItems", invoicePage.getTotalElements());
            m.addAttribute("size", size);
            m.addAttribute("searchTerm", searchTerm);
            m.addAttribute("sortField", sortField);
            m.addAttribute("sortDir", sortDir);
            m.addAttribute("acc",account);
        } catch (Exception e) {
            return "ListInvoiceForEmployee";
        }

        return "ListInvoiceForEmployee";
    }
    @PostMapping("/InvoiceDetail")
    public String goToInvoiceDetail (@RequestParam int invoiceID, HttpServletRequest request,Model model){
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Invoice invoice = invoiceService.getInvoiceById(invoiceID);
        Customer customer= customerService.getCustomerByCustomerID(invoice.getCustomerID());
        Employee employee=employeeService.getEmployeeByEmployeeID(invoice.getEmployeeID());
        List<InvoiceItem> invoiceItems=invoiceItemService.findByInvoiceID(invoiceID);
        model.addAttribute("invoice",invoice);
        model.addAttribute("customer",customer);
        model.addAttribute("employee",employee);
        model.addAttribute("invoiceItems",invoiceItems);

        return "DetailInvoiceForCustomer";
    }
}
