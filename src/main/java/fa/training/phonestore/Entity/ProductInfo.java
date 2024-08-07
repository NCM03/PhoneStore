package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_info")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productInfoID")
    private int productInfoId;

    @Column(name = "ProductInfoName")
    private String productInfoName;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Description")
    private String description;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "ImportDate")
    private LocalDateTime importDate;

    @Column(name = "QuantitySold")
    private int quantitySold;

    @Column(name = "Ram")
    private int ram;

    @Column(name = "Capacity")
    private int capacity;

    @Column(name = "Color")
    private String color;

    @Column(name = "productDiscount", nullable = false, precision = 5, scale = 2)
    private BigDecimal disCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productInfoStatus", nullable = false)
    @JsonBackReference
    private ProductStatus productInfoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID", nullable = false)
    @JsonBackReference
    private Product product;

    @OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Cart> cart;

    public ProductImage getFirstProductImage(List<ProductImage> productImages) {
        if (productImages != null && !productImages.isEmpty()) {
            return productImages.get(0);
        }
        return null; // or throw an exception if preferred
    }
    public int getStatusID(ProductInfo product){
        return product.getProductInfoStatus().getStatusId();
    }
}