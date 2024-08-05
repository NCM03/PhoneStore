package fa.training.phonestore.service;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.repository.CustomerRespository;
import fa.training.phonestore.service.imp.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRespository customerRespository;

    @Override
    public Customer getCustomer(Account account) {
        return customerRespository.getCustomersByAccount(account);
    }
    @Override
    public boolean existsByEmail(String email) {
        return customerRespository.existsByEmail(email);
    }

    @Override
    public Customer getCustomerByCustomerID(int customerID) {
        return  customerRespository.getCustomersByCustomerId(customerID);
    }

    public Customer saveCustomer(Account account) {
        Customer customer = new Customer();
        customer.setAccount(account);
        return customerRespository.save(customer);
    }
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRespository.save(customer);
    }
    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRespository.getCustomersByEmail(email);
    }

}
