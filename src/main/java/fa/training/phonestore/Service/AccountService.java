package fa.training.phonestore.Service;

import fa.training.phonestore.Entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface AccountService {
Account searchUser(String username);
    Account getUser(String username);
     Page<Account> findAllAccounts(Pageable pageable);
    Page<Account> findAccountsByUsername(String username, Pageable pageable);

   Account save(Account account);
    Account update(Account account);
Account seachAccountById(int id);

}
