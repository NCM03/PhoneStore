package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    Invoice getInvoiceById(Integer id);
    int countAllByEmployeeID(int employeeID);
    long countAll();
    Page<Invoice> findAll(Integer page, Integer size);
    Page<Invoice> findByEmployeeID(int employeeID, Pageable pageable);
    Page<Invoice> findByCustomerID(int customerID,Pageable pageable);
}
