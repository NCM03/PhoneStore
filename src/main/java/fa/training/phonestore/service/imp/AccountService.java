package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface AccountService {
Account searchUser(String username);
    Account getUser(String username);
     Page<Account> findAllAccounts(Pageable pageable);
    Page<Account> findAccountsByUsername(String username, Pageable pageable);
    Account saveAccount(Account account);
   Account save(Account account);
    Account update(Account account);
Account seachAccountById(int id);

}
