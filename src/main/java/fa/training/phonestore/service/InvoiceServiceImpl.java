package fa.training.phonestore.service;

import fa.training.phonestore.entity.Invoice;
import fa.training.phonestore.repository.InvoiceRepository;
import fa.training.phonestore.service.imp.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
