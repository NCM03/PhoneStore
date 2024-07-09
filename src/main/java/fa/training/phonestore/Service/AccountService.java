package fa.training.phonestore.Service;

import fa.training.phonestore.Entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    Account save(Account account);
    Account getUser(String username);
    List<Account> findAll();
    Account findById(Long id);
    Account update(Account account);


}
