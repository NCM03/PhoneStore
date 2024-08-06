package fa.training.phonestore.repository;

import fa.training.phonestore.entity.RequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRespository extends CrudRepository<RequestEntity,Integer> {
    RequestEntity save(RequestEntity requestEntity);
    Page<RequestEntity> findByCustomerID(int customerID, Pageable pageable);
    Page<RequestEntity> findByTitle(String title,Pageable pageable);
    Page<RequestEntity> findByCustomerIDAndRequestDateBetween(int customerID, LocalDateTime fromDate,LocalDateTime toDate, Pageable pageable);
    Page<RequestEntity> findByCustomerIDAndRequestDateAfter(int customerID, LocalDateTime fromDate,Pageable pageable);
    Page<RequestEntity> findByCustomerIDAndRequestDateBefore(int customerID, LocalDateTime toDate,Pageable pageable);
    Page<RequestEntity> findByTitleAndRequestDateBetween(String title, LocalDateTime fromDate,LocalDateTime toDate, Pageable pageable);
    Page<RequestEntity> findByTitleAndRequestDateAfter(String title, LocalDateTime fromDate,Pageable pageable);
    Page<RequestEntity> findByTitleAndAndRequestDateBefore(String title, LocalDateTime toDate,Pageable pageable);
    Page<RequestEntity> findAll(Pageable pageable);
    RequestEntity findByRequestID(int requestID);
    int countByEmployeeID(int id);
    long count();
}
