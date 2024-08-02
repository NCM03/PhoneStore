package fa.training.phonestore.repository;

import fa.training.phonestore.entity.RequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRespository extends CrudRepository<RequestEntity,Integer> {


}
