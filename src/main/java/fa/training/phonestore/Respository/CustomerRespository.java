package fa.training.phonestore.Respository;

import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRespository extends CrudRepository<Customer,Long> {
    Customer getCustomersByAccount(Account account);
    Customer getCustomersByEmail (String email);
    Customer save(Customer customer);

}
