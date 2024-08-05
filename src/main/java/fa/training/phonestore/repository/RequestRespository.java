package fa.training.phonestore.repository;

import fa.training.phonestore.entity.RequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRespository extends CrudRepository<RequestEntity,Integer> {
    RequestEntity save(RequestEntity requestEntity);
    Page<RequestEntity> findByCustomerID(int customerID, Pageable pageable);
    Page<RequestEntity> findByTitle(String title,Pageable pageable);
    Page<RequestEntity> findAll(Pageable pageable);
    RequestEntity findByRequestID(int requestID);

}
