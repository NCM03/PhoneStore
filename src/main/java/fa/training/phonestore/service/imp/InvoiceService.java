package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.Employee;
import fa.training.phonestore.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface InvoiceService {
    Page<Invoice> findInvoiceByCustomerID(int id , Pageable pageable);
    Invoice getInvoiceById(Integer id);
    int countAllByEmployee(Employee employee);
    long countAll();
    Page<Invoice> findAll(Integer page, Integer size);
    Page<Invoice> findByEmployee(Employee employee, Pageable pageable);
    Page<Invoice> findByCustomer(Customer customer, Pageable pageable);
    Page<Invoice> findByEmployeeAndCustomer(Employee employee, Customer customer, Pageable pageable);
    Page<Invoice> findByEmployeeAndDateRange(Employee employee, LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Invoice> findByCustomerNameAndDateRange(String name, LocalDate startDate, LocalDate endDate, Pageable pageable);
    List<Invoice> findByEmployee(Employee employee, Sort sort);
    List<Invoice> findByCustomerNameAndDateRange(String customerName, LocalDate startDate, LocalDate endDate, Sort sort);
}
