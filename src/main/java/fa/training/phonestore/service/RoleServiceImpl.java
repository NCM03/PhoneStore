package fa.training.phonestore.service;

import fa.training.phonestore.entity.Role;
import fa.training.phonestore.repository.RoleRespository;
import fa.training.phonestore.service.imp.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRespository roleRespository;
    @Override
    public Role getRoleById(int id) {
        Role role = roleRespository.findById(id).get();
        return role;
    }
}
