package fa.training.phonestore.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation = Paths.get("src/main/resources/static/img/ProductImg");
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(MultipartFile file, String name) {
        try {
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            String newFileName = name + extension;
            Path destinationFile = this.rootLocation.resolve(Paths.get(newFileName)).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String name) {
        try {
            // Tạo đường dẫn đầy đủ bằng cách nối rootLocation và name
            Path file = this.rootLocation.resolve(Paths.get(name)).normalize().toAbsolutePath();
            System.out.println("Full file path: " + file);

            // Kiểm tra xem tệp có tồn tại không trước khi xóa
            if (Files.exists(file)) {
                Files.deleteIfExists(file);
                System.out.println("File deleted: " + file);
            } else {
                System.err.println("File not found: " + file);
            }
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + name);
            e.printStackTrace();
            throw new RuntimeException("Failed to delete file: " + name, e);
        }
    }
}


