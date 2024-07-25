package fa.training.phonestore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_support")
public class ProductSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productSupID")
    private int productSupId;

    @Column(name = "productID")
    private int  productID;

    @Column(name = "ProductSupName")
    private String productSupName;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Description")
    private String description;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "ImportDate")
    private Date importDate;

    @Column(name = "QuantitySold")
    private int quantitySold;

    @Column(name = "Ram")
    private int ram;

    @Column(name = "Capacity")
    private int capacity;

    @Column(name = "Color")
    private String color;

    @OneToMany(mappedBy = "productSupport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages;

    public ProductImage getFirstProductImage(List <ProductImage> productImages) {
        if (productImages != null && !productImages.isEmpty()) {
            return productImages.get(0);
        }
        return null; // or throw an exception if preferred
    }

}
