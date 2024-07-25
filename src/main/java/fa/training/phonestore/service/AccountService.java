package fa.training.phonestore.service;

import fa.training.phonestore.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
Account searchUser(String username);
    Account getUser(String username);
    List<Account> findAll();
   Account save(Account account);
    Account update(Account account);
Account seachAccountById(int id);

}
