package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee getEmployeeByAccount(Account account);
}
