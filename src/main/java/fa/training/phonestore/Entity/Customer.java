package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fa.training.phonestore.Constraint.EntityConstraint.EmailConstraint;
import fa.training.phonestore.Constraint.EntityConstraint.Gender;
import fa.training.phonestore.Constraint.EntityConstraint.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name="CustomerID")
    private int customerId;
    @Column(name = "Phone")
    @Phone(message="Phone has wrong format")
    private String phone;
    @Column(name="Email")
    @EmailConstraint(message = "Email has wrong format")
    private String email;
    @Column(name="CustomerName")
    private String name;
    @Column(name="Gender")
    @Gender(message="Male or Female or Other")
    private String gender;
    @Column(name="DateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @NotBlank(message="Address is not blank")
    @Column(name="Address")
    private String address;
    @OneToOne
    @JoinColumn(name = "AccountID")
    private Account account;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Cart> Cart;
}
