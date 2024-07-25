package fa.training.phonestore.service;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Customer;
import fa.training.phonestore.repository.CustomerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRespository customerRespository;

    @Override
    public Customer getCustomer(Account account) {
        return customerRespository.getCustomersByAccount(account);
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
