package fa.training.phonestore.Respository;

import fa.training.phonestore.Entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRespository extends CrudRepository<Account, Long> {
    @EntityGraph(attributePaths = {"role"})
    Optional<Account> findByUsername(String username);
}
