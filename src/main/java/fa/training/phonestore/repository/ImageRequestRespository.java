package fa.training.phonestore.repository;

import fa.training.phonestore.entity.ImageRequest;
import fa.training.phonestore.service.imp.ImageRequestService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRequestRespository extends CrudRepository<ImageRequest,Integer> {
List<ImageRequest> findByRequestID(int requestID);
}
