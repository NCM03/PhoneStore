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
@Table(name = "Product_ProductStatus")
public class Product_ProductStatus {

    @EmbeddedId
    private ProductProductStatusId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "ProductID")
    private Product product;

    @ManyToOne
    @MapsId("statusId")
    @JoinColumn(name = "StatusID")
    private ProductStatus productStatus;

}