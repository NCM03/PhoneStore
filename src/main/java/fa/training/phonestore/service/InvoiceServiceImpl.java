package fa.training.phonestore.service;

import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.entity.Employee;
import fa.training.phonestore.entity.Invoice;
import fa.training.phonestore.repository.InvoiceRepository;
import fa.training.phonestore.service.imp.InvoiceService;
import org.hibernate.validator.constraints.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
@Autowired
        InvoiceRepository invoiceRepository;

    @Override
    public Page<Invoice> findInvoiceByCustomerID(int id, Pageable pageable) {
        return invoiceRepository.findInvoiceByCustomer_CustomerId(id ,pageable);
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        return invoiceRepository.getInvoiceByInvoiceId(id);
    }

    @Override
    public int countAllByEmployee(Employee employee) {
        return invoiceRepository.countByEmployee(employee);
    }

    @Override
    public long countAll() {
        return invoiceRepository.count();
    }

    @Override
    public Page<Invoice> findAll(Integer page, Integer size) {
        return invoiceRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    @Override
    public Page<Invoice> findByEmployee(Employee employee, Pageable pageable) {
        return invoiceRepository.findByEmployee(employee, pageable);
    }

    @Override
    public Page<Invoice> findByCustomer(Customer customer, Pageable pageable) {
        return invoiceRepository.findByCustomer(customer, pageable);
    }

    @Override
    public Page<Invoice> findByEmployeeAndCustomer(Employee employee, Customer customer, Pageable pageable) {
        return invoiceRepository.findByEmployeeAndCustomer(employee, customer, pageable);
    }

    @Override
    public Page<Invoice> findByEmployeeAndDateRange(Employee employee, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return invoiceRepository.findByEmployeeAndInvoiceDateBetween(employee, startDate, endDate, pageable);
    }


    @Override
    public Page<Invoice> findByCustomerNameAndDateRange(String customer, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return invoiceRepository.findByCustomer_NameContainingIgnoreCaseAndInvoiceDateBetween(customer, startDate, endDate, pageable);
    }

    @Override
    public List<Invoice> findByEmployee(Employee employee, Sort sort) {
        return invoiceRepository.findByEmployee(employee, sort);
    }

    @Override
    public List<Invoice> findByCustomerNameAndDateRange(String customerName, LocalDate startDate, LocalDate endDate, Sort sort) {
        return invoiceRepository.findByCustomerContainingAndInvoiceDateBetween(customerName, startDate, endDate, sort);
    }


}
