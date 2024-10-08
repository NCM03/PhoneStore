package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ProductStatus")
public class ProductStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StatusID", nullable = false)
    private Integer statusId;

    @Column(name = "StatusName", nullable = false, length = 50)
    private String statusName;

    @OneToMany(mappedBy = "productInfoStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductInfo> productInfos;
}