package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.ImageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageRequestService {
    ImageRequest saveImageRequest(ImageRequest imageRequest);
    List<ImageRequest> findByRequestID(int requestID);
}
