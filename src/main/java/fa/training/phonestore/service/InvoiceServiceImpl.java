package fa.training.phonestore.service;

import fa.training.phonestore.entity.Invoice;
import fa.training.phonestore.repository.InvoiceRepository;
import fa.training.phonestore.service.imp.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
@Autowired
        InvoiceRepository invoiceRepository;
    @Override
    public Invoice getInvoiceById(Integer id) {
        return invoiceRepository.getInvoiceByInvoiceId(id);
    }

    @Override
    public int countAllByEmployeeID(int employeeID) {
        return invoiceRepository.countAllByEmployeeID(employeeID);
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
    public Page<Invoice> findByEmployeeID(int employeeID, Pageable pageable) {
        return invoiceRepository.findByEmployeeID(employeeID, pageable);
    }

    @Override
    public Page<Invoice> findByCustomerID(int customerID, Pageable pageable) {
        return invoiceRepository.findByCustomerID(customerID, pageable);
    }


}
