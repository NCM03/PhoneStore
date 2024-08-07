package fa.training.phonestore.service.imp;


import fa.training.phonestore.entity.RequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface RequestEntityService {
    Page<RequestEntity> findByCustomerIDAndDateRange(int customerID, LocalDateTime fromDate,LocalDateTime toDate, Pageable pageable);
    Page<RequestEntity> findByCustomerIDAndFromDate(int customerID, LocalDateTime fromDate,Pageable pageable);
    Page<RequestEntity> findByCustomerIDAndToDate(int customerID, LocalDateTime toDate,Pageable pageable);
    Page<RequestEntity> findByTitleAndDateRange(String title, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
    Page<RequestEntity> findByTitleAndFromDate(String title, LocalDateTime fromDate,Pageable pageable);
    Page<RequestEntity> findByTitleAndToDate(String title, LocalDateTime toDate,Pageable pageable);
    RequestEntity save(RequestEntity requestEntity);
    Page<RequestEntity> findByCustomerID(int customerID, Pageable pageable);
    Page<RequestEntity> findByTitle(String title,Pageable pageable);
    Page<RequestEntity> findAll(Pageable pageable);
    RequestEntity findByRequestID(int requestID);
    int countByEmployeeID(int id);
    long count();
    long countByStatus(int status);
}
