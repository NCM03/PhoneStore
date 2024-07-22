package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRespository extends CrudRepository<Customer,Long> {
    Customer getCustomersByAccount(Account account);
    Customer getCustomersByEmail (String email);
    Customer save(Customer customer);

}
