package fa.training.phonestore.service;

import fa.training.phonestore.entity.InvoiceItem;
import fa.training.phonestore.repository.ImageRequestRespository;
import fa.training.phonestore.repository.InvoiceItemRepository;
import fa.training.phonestore.service.imp.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemImpl implements InvoiceItemService {
    @Autowired
    InvoiceItemRepository invoiceItemRepository;
    @Override
    public List<InvoiceItem> findByInvoiceID(Integer invoiceID) {
        return invoiceItemRepository.findByInvoiceID(invoiceID);

    }
}
