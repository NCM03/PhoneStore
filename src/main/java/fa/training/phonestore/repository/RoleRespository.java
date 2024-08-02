package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRespository extends CrudRepository<Role, Integer>
{
}
