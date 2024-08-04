package fa.training.phonestore.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "ProductInfoID", nullable = false)
    private int productInfoID;

    @Column(name = "ImageURL")
    private String imageURL;

    @Column(name = "ProductID")
    private int productID;

    @ManyToOne
    @JoinColumn(name = "ProductInfoID", nullable = true, insertable = false, updatable = false)
    @JsonBackReference
    private ProductInfo productInfo;

}
