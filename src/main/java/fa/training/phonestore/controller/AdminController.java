package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Brand;
import fa.training.phonestore.entity.Category;
import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.imp.BrandService;
import fa.training.phonestore.service.imp.CategoryServiceImp;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryServiceImp categoryServiceImp;

    JwtUtils jwtUtils = new JwtUtils();
    HelperToken helperToken;
    @GetMapping("/ManageBrand")
    public String getListBrand(Model m,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(required = false) Integer selectedSize,
                               @RequestParam(required = false) String searchTerm,
                               @RequestParam(defaultValue = "brandId") String sortField,
                               @RequestParam(defaultValue = "desc") String sortDir) {

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

            Page<Brand> brandPage;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                brandPage = brandService.findBrandsByBrandNameContaining(searchTerm, pageable);
            } else {
                brandPage = brandService.findAllBrands(pageable);
            }

            // Thêm thuộc tính vào model
            m.addAttribute("brandList", brandPage.getContent());
            m.addAttribute("currentPage", page);
            m.addAttribute("totalPages", brandPage.getTotalPages());
            m.addAttribute("totalItems", brandPage.getTotalElements());
            m.addAttribute("size", size);
            m.addAttribute("searchTerm", searchTerm);
            m.addAttribute("sortField", sortField);
            m.addAttribute("sortDir", sortDir);

            Brand brand = new Brand();
            m.addAttribute("brand", brand);
        } catch (Exception e) {
            return "ManageBrandForAdmin";
        }

        return "ManageBrandForAdmin";
    }


    @PostMapping("/updateBrand")
    public ResponseEntity<Map<String, Object>> updateBrand(
            @Valid @RequestBody Brand brand,
            BindingResult result, RedirectAttributes redirectAttributes) {


        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("status", "error");
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            response.put("errors", errors);
            return ResponseEntity.ok(response);
        }
        if (brandService.existsBrandByBrandName( brand.getName()) == true){
            Map<String, String> errors = new HashMap<>();
            errors.put("name", "Brand name already exists");
            response.put("errors", errors);
            return ResponseEntity.ok(response);
        }
        brandService.saveBrand(brand);
        response.put("status", "success");
        redirectAttributes.addFlashAttribute("messageSS", "Brand updated successfully");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/addBrand")
    public  ResponseEntity<Map<String, Object>> addCategory(
            @Valid @RequestBody Brand brand,
            BindingResult result, RedirectAttributes redirectAttributes) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("status", "error");
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            response.put("errors", errors);
            return ResponseEntity.ok(response);
        }
        if (brandService.existsBrandByBrandName(brand.getName()) == true) {
            Map<String, String> errors = new HashMap<>();
            errors.put("name", "Brand name already exists");
            response.put("errors", errors);
            return ResponseEntity.ok(response);
        }
                brandService.saveBrand(brand);
        response.put("status", "success");
        redirectAttributes.addFlashAttribute("messageSS", "Brand Add successfully");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/ManageCategory")
    public String getListCategory(Model m,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(required = false) Integer selectedSize,
                                  @RequestParam(required = false) String searchTerm,
                                  @RequestParam(required = false) String sortField,
                                  @RequestParam(required = false) String sortDir) {
        try {
            if (selectedSize != null) {
                size = selectedSize;
            }

            // Mặc định sắp xếp theo categoryID giảm dần nếu không có sortField hoặc sortDir
            if (sortField == null || sortField.isEmpty()) {
                sortField = "categoryId";
            }
            if (sortDir == null || sortDir.isEmpty()) {
                sortDir = "desc";
            }

            // Tạo yêu cầu phân trang với sắp xếp
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<Category> categoryPage;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                categoryPage = categoryServiceImp.findCategoriesByCategoryNameContaining(searchTerm, pageable);
            } else {
                categoryPage = categoryServiceImp.findAllCategories(pageable);
            }

            // Thêm thuộc tính vào model
            m.addAttribute("categoryList", categoryPage.getContent());
            m.addAttribute("currentPage", page);
            m.addAttribute("totalPages", categoryPage.getTotalPages());
            m.addAttribute("totalItems", categoryPage.getTotalElements());
            m.addAttribute("size", size);
            m.addAttribute("searchTerm", searchTerm);
            m.addAttribute("sortField", sortField);
            m.addAttribute("sortDir", sortDir);
            Category category = new Category();
            m.addAttribute("category", category);
        } catch (Exception e) {
            return "ManageCategoryForAdmin";
        }
        return "ManageCategoryForAdmin";
    }
    @PostMapping("/addCategory")
public  ResponseEntity<Map<String, Object>> addCategory(
        @Valid @RequestBody Category category,
        BindingResult result, RedirectAttributes redirectAttributes) {
    Map<String, Object> response = new HashMap<>();

    if (result.hasErrors()) {
        response.put("status", "error");
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        response.put("errors", errors);
        return ResponseEntity.ok(response);
    }
    if (categoryServiceImp.existsCategoryByCategoryName(category.getName()) == true) {
        Map<String, String> errors = new HashMap<>();
        errors.put("categoryName", "Category name already exists");
        response.put("errors", errors);
        return ResponseEntity.ok(response);
    }
    categoryServiceImp.saveCategory(category);
    response.put("status", "success");
    redirectAttributes.addFlashAttribute("messageSS", "Category Add successfully");
    return ResponseEntity.ok(response);
}

    @PostMapping("/updateCategory")
    public ResponseEntity<Map<String, Object>> updateCategory(
            @Valid @RequestBody Category category,
            BindingResult result, RedirectAttributes redirectAttributes) {


        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("status", "error");
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            response.put("errors", errors);
            return ResponseEntity.ok(response);
        }
        if (categoryServiceImp.existsCategoryByCategoryName(category.getName()) == true) {
            Map<String, String> errors = new HashMap<>();
            errors.put("categoryName", "Category name already exists");
            response.put("errors", errors);
            return ResponseEntity.ok(response);
        }
        categoryServiceImp.saveCategory(category);
        response.put("status", "success");
        redirectAttributes.addFlashAttribute("messageSS", "Category updated successfully");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/exportBrandsToExcel")
    public ResponseEntity<byte[]> exportBrandsToExcel() throws IOException {
        List<Brand> brandList = brandService.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Brands");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Brand ID");
            headerRow.createCell(1).setCellValue("Brand Name");

            int rowNum = 1;
            for (Brand brand : brandList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(brand.getBrandId());
                row.createCell(1).setCellValue(brand.getName());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            byte[] excelData = out.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=brands.xlsx");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            return ResponseEntity.ok().headers(headers).body(excelData);
        }
    }
}


