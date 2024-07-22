package fa.training.phonestore.service;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.Respository.AccountRespository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AccountRespository accountRespository;
    @Override
    public Account save(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return accountRespository.save(account);
    }



    static Account unwrapUsser(Optional<Account> account, long id) {

        if (account.isPresent()) {
            return account.get();
        } else throw new EntityNotFoundException();

    }
@Transactional
    public Account getUser(String username) {

        Optional<Account> user = accountRespository.findByUsername(username);
        return unwrapUsser(user, 404L);
    }
    @Override
    public List<Account> findAll() {
        return (List<Account>) accountRespository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRespository.findById(id).orElse(null);
    }

    @Override
    public Account update(Account account) {
        return accountRespository.save(account);
    }
}
