package fa.training.phonestore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductProductStatusId implements Serializable {

    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "StatusID")
    private Integer statusId;
}