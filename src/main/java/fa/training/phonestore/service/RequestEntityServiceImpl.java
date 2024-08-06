package fa.training.phonestore.service;

import fa.training.phonestore.entity.RequestEntity;
import fa.training.phonestore.repository.RequestRespository;
import fa.training.phonestore.service.imp.RequestEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestEntityServiceImpl implements RequestEntityService {
    @Autowired
    RequestRespository requestRespository;

    @Override
    public Page<RequestEntity> findByCustomerIDAndDateRange(int customerID, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable) {
        return requestRespository.findByCustomerIDAndRequestDateBetween(customerID,fromDate,toDate,pageable);
    }

    @Override
    public Page<RequestEntity> findByCustomerIDAndFromDate(int customerID, LocalDateTime fromDate, Pageable pageable) {
        return requestRespository.findByCustomerIDAndRequestDateAfter(customerID,fromDate,pageable);
    }

    @Override
    public Page<RequestEntity> findByCustomerIDAndToDate(int customerID, LocalDateTime toDate, Pageable pageable) {
        return requestRespository.findByCustomerIDAndRequestDateBefore(customerID,toDate,pageable);
    }

    @Override
    public Page<RequestEntity> findByTitleAndDateRange(String title, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable) {
        return requestRespository.findByTitleAndRequestDateBetween(title,fromDate,toDate,pageable);
    }

    @Override
    public Page<RequestEntity> findByTitleAndFromDate(String title, LocalDateTime fromDate, Pageable pageable) {
        return requestRespository.findByTitleAndRequestDateAfter(title,fromDate,pageable);
    }

    @Override
    public Page<RequestEntity> findByTitleAndToDate(String title, LocalDateTime toDate, Pageable pageable) {
       return  requestRespository.findByTitleAndAndRequestDateBefore(title,toDate,pageable);
    }

    @Override
    public RequestEntity save(RequestEntity requestEntity) {
        return requestRespository.save(requestEntity);
    }

    @Override
    public Page<RequestEntity> findByCustomerID(int customerID, Pageable pageable) {
        return requestRespository.findByCustomerID(customerID, pageable);
    }

    @Override
    public Page<RequestEntity> findByTitle(String title, Pageable pageable) {
        return requestRespository.findByTitle(title, pageable);
    }

    @Override
    public Page<RequestEntity> findAll(Pageable pageable) {
        return requestRespository.findAll(pageable);
    }

    @Override
    public RequestEntity findByRequestID(int requestID) {
        return requestRespository.findByRequestID(requestID);
    }
 @Override
    public int countByEmployeeID(int id){
        return requestRespository.countByEmployeeID(id);
    }
    @Override
    public long count(){
        return requestRespository.count();
    }



}
