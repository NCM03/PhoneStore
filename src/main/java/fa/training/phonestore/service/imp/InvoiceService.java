package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Invoice;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    Invoice getInvoiceById(Integer id);
    int countAllByEmployeeID(int employeeID);
    long countAll();
}
