package fa.training.phonestore.entity;

import fa.training.phonestore.Constraint.EntityConstraint.ImageNameConstraint;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Image_Request")
public class ImageRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageID")
    private int imageID;
    @Column(name = "ImageURL")
    private String imageURL;

    @Column(name = "Image_Name")
    private String imageName;
    @Column(name = "RequestID")
    private int requestID;


}
