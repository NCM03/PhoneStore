package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Role;
import org.springframework.stereotype.Service;
@Service
public interface RoleService {

        Role getRoleById(int id);


}
