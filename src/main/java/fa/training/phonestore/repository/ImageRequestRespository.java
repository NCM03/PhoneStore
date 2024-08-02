package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ImageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRequestRespository extends CrudRepository<ImageRequest,Integer> {

}
