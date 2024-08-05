package fa.training.phonestore.service;

import fa.training.phonestore.entity.ImageRequest;
import fa.training.phonestore.repository.ImageRequestRespository;
import fa.training.phonestore.service.imp.ImageRequestService;
import fa.training.phonestore.service.imp.RequestEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageRequestServiceImpl implements ImageRequestService {
    @Autowired
    ImageRequestRespository imageRequestRespository;
    @Override
    public ImageRequest saveImageRequest(ImageRequest imageRequest) {
        return imageRequestRespository.save(imageRequest);
    }

    @Override
    public List<ImageRequest> findByRequestID(int requestID) {
        return imageRequestRespository.findByRequestID(requestID);
    }
}
