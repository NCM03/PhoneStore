package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Invoice getInvoiceByInvoiceId(Integer invoiceId);
    int countAllByEmployeeID(int employeeID);
    long count();
    Page<Invoice> findAll(Pageable pageable);
    Page<Invoice> findByEmployeeID(int employeeID, Pageable pageable);
    Page<Invoice> findByCustomerID(int customerID, Pageable pageable);
}
