package fa.training.phonestore.helper;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class FileUploadUtil {
    private static String uploadDir;

    @PostConstruct
    public void init() {
        // Lấy đường dẫn tới thư mục gốc của project
        String projectDir = System.getProperty("user.dir");
        // Xác định thư mục static
        String staticDir = projectDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static";
        // Tạo đường dẫn tới thư mục uploads
        uploadDir = staticDir + File.separator + "uploads";

        // Tạo thư mục nếu nó chưa tồn tại
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static String getUploadDir() {
        return uploadDir;
    }
}
