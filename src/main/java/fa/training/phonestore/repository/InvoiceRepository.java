package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.Employee;
import fa.training.phonestore.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Invoice getInvoiceByInvoiceId(Integer invoiceId);
    int countByEmployee(Employee employee);
    long count();
    Page<Invoice> findInvoiceByCustomer_CustomerId(int id,Pageable pageable);
    Page<Invoice> findAll(Pageable pageable);
    Page<Invoice> findByEmployee(Employee employee, Pageable pageable);
    Page<Invoice> findByCustomer(Customer customer, Pageable pageable);
    Page<Invoice> findByEmployeeAndCustomer(Employee employee, Customer customer, Pageable pageable);
    Page<Invoice> findByEmployeeAndInvoiceDateBetween(Employee employee, LocalDate startDate, LocalDate endDate, Pageable pageable);
    @Query("SELECT i FROM Invoice i JOIN i.customer c WHERE " +
            "(LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL) AND " +
            "(i.invoiceDate BETWEEN :startDate AND :endDate OR :startDate IS NULL OR :endDate IS NULL) " +
            "ORDER BY i.invoiceDate DESC")
    Page<Invoice> findByCustomer_NameContainingIgnoreCaseAndInvoiceDateBetween( String name,  LocalDate startDate,LocalDate endDate, Pageable pageable);
    List<Invoice> findByEmployee(Employee employee, Sort sort);
    List<Invoice> findByCustomerContainingAndInvoiceDateBetween(String customerName, LocalDate startDate, LocalDate endDate, Sort sort);
}

