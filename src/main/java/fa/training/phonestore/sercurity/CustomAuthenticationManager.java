package fa.training.phonestore.sercurity;

import fa.training.phonestore.entity.Account;

import fa.training.phonestore.service.AccountService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Account account = accountService.getUser(authentication.getName());
        Hibernate.initialize(account.getRole());
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), account.getPassword())) {
            throw new BadCredentialsException("You provided incorrect password");
        }
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(account.getRole().getRoleName().trim()));
        return new UsernamePasswordAuthenticationToken(account, null, authorities);
    }
}