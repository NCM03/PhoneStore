package fa.training.phonestore.controller;

import fa.training.phonestore.entity.*;
import fa.training.phonestore.helper.FileUploadUtil;
import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.imp.CustomerService;
import fa.training.phonestore.service.imp.ImageRequestService;
import fa.training.phonestore.service.imp.InvoiceService;
import fa.training.phonestore.service.imp.RequestEntityService;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class RequestController {
    @Autowired
    CustomerService customerService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    RequestEntityService requestEntityService;
    @Autowired
    ImageRequestService imageRequestService;
    JwtUtils jwtUtils = new JwtUtils();
    HelperToken helperToken;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @GetMapping("/Customer/Request")
    public String listRequest(Model m, HttpServletRequest request,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(required = false) Integer selectedSize,
                              @RequestParam(required = false) String searchTerm,
                              @RequestParam(required = false) String sortField,
                              @RequestParam(required = false) String sortDir) {
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Customer customer = customerService.getCustomer(account);
        // Update size if selectedSize is provided
        try {
            if (selectedSize != null) {
                size = selectedSize;
            }

            // Mặc định sắp xếp theo categoryID giảm dần nếu không có sortField hoặc sortDir
            if (sortField == null || sortField.isEmpty()) {
                sortField = "requestID";
            }
            if (sortDir == null || sortDir.isEmpty()) {
                sortDir = "desc";
            }

            // Tạo yêu cầu phân trang với sắp xếp
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<RequestEntity> requestPage;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                requestPage =  requestEntityService.findByTitle(searchTerm, pageable);
            } else {
                requestPage =  requestEntityService.findByCustomerID(customer.getCustomerId(), pageable);
            }

            // Thêm thuộc tính vào model
            m.addAttribute("reList", requestPage.getContent());
            m.addAttribute("currentPage", page);
            m.addAttribute("totalPages", requestPage.getTotalPages());
            m.addAttribute("totalItems", requestPage.getTotalElements());
            m.addAttribute("size", size);
            m.addAttribute("searchTerm", searchTerm);
            m.addAttribute("sortField", sortField);
            m.addAttribute("sortDir", sortDir);
           m.addAttribute("acc", account);
        } catch (Exception e) {
            return "ListApplicationForCustomer";
        }
        return "ListApplicationForCustomer";
    }
    @GetMapping("/Employee/Request")
    public String listRequestForEmployee(Model m, HttpServletRequest request,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(required = false) Integer selectedSize,
                              @RequestParam(required = false) String searchTerm,
                              @RequestParam(required = false) String sortField,
                              @RequestParam(required = false) String sortDir) {
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Customer customer = customerService.getCustomer(account);
        // Update size if selectedSize is provided
        try {
            if (selectedSize != null) {
                size = selectedSize;
            }

            // Mặc định sắp xếp theo categoryID giảm dần nếu không có sortField hoặc sortDir
            if (sortField == null || sortField.isEmpty()) {
                sortField = "requestDate";
            }
            if (sortDir == null || sortDir.isEmpty()) {
                sortDir = "desc";
            }

            // Tạo yêu cầu phân trang với sắp xếp
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<RequestEntity> requestPage;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                requestPage =  requestEntityService.findByTitle(searchTerm, pageable);
            } else {
                requestPage =  requestEntityService.findAll(pageable);
            }

            // Thêm thuộc tính vào model
            m.addAttribute("reList", requestPage.getContent());
            m.addAttribute("currentPage", page);
            m.addAttribute("totalPages", requestPage.getTotalPages());
            m.addAttribute("totalItems", requestPage.getTotalElements());
            m.addAttribute("size", size);
            m.addAttribute("searchTerm", searchTerm);
            m.addAttribute("sortField", sortField);
            m.addAttribute("sortDir", sortDir);
            m.addAttribute("acc", account);
        } catch (Exception e) {
            return "ListApplicationForEmployee";
        }
        return "ListApplicationForEmployee";
    }

    @GetMapping("/Customer/Application")
    public String getRequest(Model m, HttpServletRequest request) {
        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        RequestEntity requestEntity = new RequestEntity();
        Customer customer = customerService.getCustomer(account);
        requestEntity.setCustomerID(customer.getCustomerId());
        m.addAttribute("requestEntity", requestEntity);
        m.addAttribute("acc", account);
        return "ApplicationForCustomer";
    }

    @PostMapping("/Customer/SendApplication")
    public ResponseEntity<?> sendApplication(@ModelAttribute RequestEntity requestEntity,
                                             @RequestParam("images") List<MultipartFile> images,
                                             HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        // Đặt thời gian hiện tại cho requestDate
        requestEntity.setRequestDate(LocalDateTime.now());

        // Validate dữ liệu cơ bản
        if (requestEntity.getTitle() == null || requestEntity.getTitle().trim().isEmpty()) {
            errors.put("title", "Tiêu đề không được để trống");
        }
        if (requestEntity.getDescription() == null || requestEntity.getDescription().trim().isEmpty()) {
            errors.put("description", "Mô tả không được để trống");
        }
        if (requestEntity.getInvoiceID() == null) {
            errors.put("invoiceID", "ID Invoice không được để trống");
        } else {
            Invoice invoice = invoiceService.getInvoiceById(requestEntity.getInvoiceID());
            if (invoice == null) {
                errors.put("invoiceID", "Hoa don nay khong ton tai");
            } else {
                if (invoice.getCustomerID() != requestEntity.getCustomerID()) {
                    errors.put("invoiceID                                                                                                           ", "Hoa don nay khong phai cua ban");
                }
            }
        }
        if (images == null || images.isEmpty()) {
            errors.put("images", "Vui lòng tải lên ít nhất một ảnh");
        } else {
            for (MultipartFile image : images) {
                String fileName = image.getOriginalFilename();
                if (fileName == null || !fileName.matches(".*\\.(jpg|jpeg|png)$")) {
                    errors.put("images", "Chỉ cho phép tệp ảnh có định dạng .jpg, .jpeg, .png");
                    break;
                }
            }
        }

        if (!errors.isEmpty()) {
            response.put("status", "error");
            response.put("errors", errors);
            return ResponseEntity.ok(response);
        }

        requestEntity.setStatus(1);
        Integer employeeID = requestEntity.getEmployeeID();
        if (employeeID != null && employeeID == 0) {
            requestEntity.setEmployeeID(null);
        }
        requestEntity = requestEntityService.save(requestEntity);

        List<ImageRequest> imageRequests = new ArrayList<>();
        try {
            String uploadPath = FileUploadUtil.getUploadDir();

            for (MultipartFile image : images) {
                String originalFilename = image.getOriginalFilename();
                if (originalFilename != null) {
                    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
                    String newFilename = UUID.randomUUID().toString() + fileExtension;
                    File file = new File(uploadPath + File.separator + newFilename);

                    // Thêm log trước khi lưu tệp
                    System.out.println("Bắt đầu lưu tệp: " + originalFilename);

                    image.transferTo(file);

                    // Thêm log sau khi lưu tệp
                    System.out.println("Tệp đã được lưu: " + file.getAbsolutePath());

                    ImageRequest imageRequest = new ImageRequest();
                    imageRequest.setImageURL("/uploads/" + newFilename);
                    imageRequest.setImageName(originalFilename);
                    imageRequest.setRequestID(requestEntity.getRequestID());
                    imageRequestService.saveImageRequest(imageRequest);
                    imageRequests.add(imageRequest);
                }
            }
            response.put("status", "success");
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("errors", Collections.singletonMap("general", "Đã xảy ra lỗi khi xử lý yêu cầu"));
            return ResponseEntity.internalServerError().body(response);
        }
    }

}
