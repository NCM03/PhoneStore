package fa.training.phonestore.service.imp;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface StorageService {
    void init();
    void store(MultipartFile file, String name);
    void delete(String name);
}