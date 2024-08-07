package fa.training.phonestore.service;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Employee;
import fa.training.phonestore.repository.EmployeeRepository;
import fa.training.phonestore.service.imp.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    EmployeeRepository employeeRespository;
    @Override
    public Employee getEmployeeByAccount(Account account) {
        return employeeRespository.getEmployeeByAccount(account);   }

    @Override
    public Employee getEmployeeByEmployeeID(int id) {
        return employeeRespository.getEmployeeByEmployeeId(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRespository.existsByEmail(email);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRespository.save(employee);
    }
}
