package fa.training.phonestore.Service;

import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer getCustomer(Account account);
    Customer saveCustomer(Customer customer);
    Customer getCustomerByEmail(String email);
    Customer saveCustomer(Account account);
    boolean existsByEmail(String email);
}
