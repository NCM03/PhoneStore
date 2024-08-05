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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Product_ProductStatusID")
    private int id;
    @Column(name = "ProductSupID")
    private int productID;

    @Column(name = "StatusID")
    private int statusID;

    @Column(name = "Reason")
    private int reason;

}