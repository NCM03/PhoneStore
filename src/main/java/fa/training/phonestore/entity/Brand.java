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
@Table(name = "Brand") // Sửa đổi ở đây
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BrandID")
    private Integer brandId;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    // Các getter và setter
}
