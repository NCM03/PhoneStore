package fa.training.phonestore.Exception;

import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Respository.AccountRespository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id,Class<?> entityClass) {
        super("Entity " + entityClass.getName() + " with id " + id + " not found");
    }


    }

