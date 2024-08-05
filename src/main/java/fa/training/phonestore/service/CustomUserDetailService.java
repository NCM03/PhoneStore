package fa.training.phonestore.service;

import fa.training.phonestore.entity.Account;
import fa.training.phonestore.repository.AccountRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
@Autowired
        AccountRespository accountRespository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Account account=accountRespository.findAccountByUsername(username);
            return org.springframework.security.core.userdetails.User
                    .withUsername(account.getUsername())
                    .password(account.getPassword())
                    .authorities((GrantedAuthority) account.getRole())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        }
}