package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer getCustomer(Account account);
    Customer saveCustomer(Customer customer);
    Customer getCustomerByEmail(String email);
    boolean existsByEmail(String email);
    Customer getCustomerByCustomerID(int customerID);
}
