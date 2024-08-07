package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.Employee;
import fa.training.phonestore.entity.Invoice;
import fa.training.phonestore.helper.HelperToken;
import fa.training.phonestore.service.imp.CustomerService;
import fa.training.phonestore.service.imp.EmployeeService;
import fa.training.phonestore.service.imp.InvoiceItemService;
import fa.training.phonestore.service.imp.InvoiceService;
import fa.training.phonestore.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ExportController {
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

    @GetMapping("/Employee/ExportInvoice")
    public void exportInvoice(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "InvoiceDate") String sortField,
            @RequestParam(defaultValue = "desc") String sortDir,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String token = helperToken.getToken(request);
        Account account = jwtUtils.decodeToken(token);
        Employee employee = employeeService.getEmployeeByAccount(account);

        Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);

        List<Invoice> invoices;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            invoices = invoiceService.findByCustomerNameAndDateRange(searchTerm, startDate, endDate, sort);
        } else {
            invoices = invoiceService.findByEmployee(employee, sort);
        }

        // Tạo workbook Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Invoices");

        // Tạo header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Loại Hóa Đơn");
        headerRow.createCell(2).setCellValue("Người mua hàng");
        headerRow.createCell(3).setCellValue("Nhân viên hóa đơn");
        headerRow.createCell(4).setCellValue("Phát sinh");
        headerRow.createCell(5).setCellValue("Phát sinh Mô tả");
        headerRow.createCell(6).setCellValue("Ngày gửi Hóa đơn");

        // Điền dữ liệu
        int rowNum = 1;
        for (Invoice invoice : invoices) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(invoice.getInvoiceId());
            row.createCell(1).setCellValue(invoice.getInvoiceType());
            row.createCell(2).setCellValue(invoice.getCustomer().getName());
            row.createCell(3).setCellValue(invoice.getEmployee().getName());
            row.createCell(4).setCellValue( invoice.getIncurred());
            row.createCell(5).setCellValue(invoice.getIncurredDescription());
            row.createCell(6).setCellValue(invoice.getInvoiceDate().toString());
        }

        // Thiết lập response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Invoices.xlsx");

        // Ghi workbook vào response output stream
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
