package fa.training.phonestore.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int productId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "ImageData")
    private String imageData;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "WarrantyPeriod")
    private Date warrantyPeriod;

    @Column(name = "CategoryID")
    private int categoryId;

    @Column(name = "Rating")
    private double rating;
}
