package fa.training.phonestore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ImageRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageID")
    private int imageID;
    @Column(name = "ImageURL")
    private String imageURL;

    @Column(name = "Image_Name")
    private String imageName;
    @ManyToOne
    @JoinColumn(name = "RequestID") // Đây là cột khóa ngoại tham chiếu đến RequestEntity
    private RequestEntity request;


}
