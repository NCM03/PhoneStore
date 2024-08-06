package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.InvoiceItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  InvoiceItemService {
    List<InvoiceItem> findByInvoiceID(Integer invoiceID) ;
}
