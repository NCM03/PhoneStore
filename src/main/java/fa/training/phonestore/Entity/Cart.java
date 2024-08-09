package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.naming.Name;

@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private int cartId;
    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "isSelected")
    private boolean isSelected;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "productInfoId", nullable = false)
    @JsonBackReference
    private ProductInfo productInfo;
}
