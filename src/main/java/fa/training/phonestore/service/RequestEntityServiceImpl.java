package fa.training.phonestore.service;

import fa.training.phonestore.entity.RequestEntity;
import fa.training.phonestore.repository.RequestRespository;
import fa.training.phonestore.service.imp.RequestEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestEntityServiceImpl implements RequestEntityService {
    @Autowired
    RequestRespository requestRespository;

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


}
