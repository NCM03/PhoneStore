package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
