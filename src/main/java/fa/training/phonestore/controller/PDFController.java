package fa.training.phonestore.controller;

import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.Invoice;
import fa.training.phonestore.entity.InvoiceItem;
import fa.training.phonestore.service.PDFService;
import fa.training.phonestore.service.imp.CustomerService;
import fa.training.phonestore.service.imp.InvoiceItemService;
import fa.training.phonestore.service.imp.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PDFController {
    @Autowired
    private final PDFService pdfService;
    @Autowired
    private final InvoiceService invoiceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InvoiceItemService invoiceItemService;
    public PDFController(PDFService pdfService, InvoiceService invoiceService) {
        this.pdfService = pdfService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/export-pdf/{invoiceId}")
    public ResponseEntity<byte[]> exportPDF(@PathVariable int invoiceId) throws Exception {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        Customer customer = customerService.getCustomerByCustomerID(invoice.getCustomer().getCustomerId());
        List<InvoiceItem> invoiceItems =invoiceItemService.findByInvoiceID(invoiceId) ;

        byte[] pdfBytes = pdfService.generatePdf(invoice, customer, invoiceItems);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "invoice.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
