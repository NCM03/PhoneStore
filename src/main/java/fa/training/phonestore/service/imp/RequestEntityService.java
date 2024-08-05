package fa.training.phonestore.service.imp;


import fa.training.phonestore.entity.RequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RequestEntityService {
    RequestEntity save(RequestEntity requestEntity);
    Page<RequestEntity> findByCustomerID(int customerID, Pageable pageable);
    Page<RequestEntity> findByTitle(String title,Pageable pageable);
    Page<RequestEntity> findAll(Pageable pageable);
    RequestEntity findByRequestID(int requestID);
}
