package fa.training.phonestore.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ProductImage")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageID")
    private Integer imageId;

    @Column(name = "ProductSupID", nullable = false)
    private int productSupID;

    @Column(name = "ImageURL")
    private String imageURL;

    @Column(name = "ProductID")
    private int productID;
    @ManyToOne
    @JoinColumn(name = "ProductSupID", nullable = true, insertable = false, updatable = false)
    private ProductSupport productSupport;

}
