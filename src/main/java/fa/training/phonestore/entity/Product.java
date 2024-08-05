package fa.training.phonestore.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime warrantyPeriod;

    @Column(name = "BrandID")
    private int brandId;

    @Column(name = "CategoryID")
    private int categoryId;

    @Column(name = "Rating")
    private double rating;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductInfo> productInfos;

    public int getTotalQuantity() {
        return totalQuantity();
    }

    private int totalQuantity() {
        int quantity = 0;
        if (productInfos != null) {
            for (ProductInfo productInfo : productInfos) {
                quantity += productInfo.getQuantity();
            }
        }
        return quantity;
    }
}
