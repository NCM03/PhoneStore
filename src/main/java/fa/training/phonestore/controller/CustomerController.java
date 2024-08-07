package fa.training.phonestore.controller;

import fa.training.phonestore.entity.*;

import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.imp.*;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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
    @Autowired
    InvoiceService invoiceService;
    @Autowired
            InvoiceItemService invoiceItemService;
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
    @GetMapping("/Customer/ManageInvoice")
    public String getListBrand(Model m,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(required = false) Integer selectedSize,
                               @RequestParam(required = false) String searchTerm,
                               @RequestParam(defaultValue = "InvoiceDate") String sortField,
                               @RequestParam(defaultValue = "desc") String sortDir,
                               HttpServletRequest request) {
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Customer customer=customerService.getCustomer(account);


        try {
            if (selectedSize != null) {
                size = selectedSize;
            }

            Sort sort = Sort.by(Sort.Direction.DESC, sortField);
            if (sortDir.equalsIgnoreCase("asc")) {
                sort = Sort.by(Sort.Direction.ASC, sortField);
            }

            Pageable pageable = PageRequest.of(page, size, sort);

            Page<Invoice> invoicePage;

                invoicePage = invoiceService.findByCustomer(customer,pageable);


            m.addAttribute("invoiceList", invoicePage.getContent());
            m.addAttribute("currentPage", page);
            m.addAttribute("totalPages", invoicePage.getTotalPages());
            m.addAttribute("totalItems", invoicePage.getTotalElements());
            m.addAttribute("size", size);
            m.addAttribute("searchTerm", searchTerm);
            m.addAttribute("sortField", sortField);
            m.addAttribute("sortDir", sortDir);
            m.addAttribute("acc", account);
        } catch (Exception e) {
            e.printStackTrace();
            return "ListInvoiceForCustomer";
        }

        return "ListInvoiceForCustomer";
    }
    @PostMapping("/Customer/InvoiceDetail")
    public String goToInvoiceDetail (@RequestParam int invoiceID, HttpServletRequest request,Model model){
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Invoice invoice = invoiceService.getInvoiceById(invoiceID);
        List<InvoiceItem> invoiceItems=invoiceItemService.findByInvoiceID(invoiceID);
        model.addAttribute("invoice",invoice);
        model.addAttribute("invoiceItems",invoiceItems);
    model.addAttribute("acc",account);
        return "DetailInvoiceforCustomer";
    }


}


