package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
        Employee getEmployeeByAccount(Account account);
        Employee getEmployeeByEmployeeID(int id);

}
