package fa.training.phonestore.service;

import com.itextpdf.text.DocumentException;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.Invoice;
import fa.training.phonestore.entity.InvoiceItem;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.util.List;

@Service
public class PDFService {


        private final TemplateEngine templateEngine;

        public PDFService(TemplateEngine templateEngine) {
            this.templateEngine = templateEngine;
        }

        public byte[] generatePdf(Invoice invoice, Customer customer, List<InvoiceItem> invoiceItems) throws IOException, DocumentException, com.lowagie.text.DocumentException {
            Context context = new Context();
            context.setVariable("invoice", invoice);
            context.setVariable("customer", customer);
            context.setVariable("invoiceItems", invoiceItems);

            String htmlContent = templateEngine.process("invoice-template", context);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            return outputStream.toByteArray();
        }
    }

