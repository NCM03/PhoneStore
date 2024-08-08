package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fa.training.phonestore.Constraint.EntityConstraint.customerconstraint.CustomerDateOfBirth;
import fa.training.phonestore.Constraint.EntityConstraint.customerconstraint.CustomerName;

import fa.training.phonestore.Constraint.EntityConstraint.EmailConstraint;
import fa.training.phonestore.Constraint.EntityConstraint.Gender;
import fa.training.phonestore.Constraint.EntityConstraint.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @Column(name = "Phone")
    @Phone(message="Phone has wrong format")
    private String phone;
    @Column(name="Email")
    @EmailConstraint(message = "Email has wrong format")
    private String email;
    @Column(name="CustomerName")
    @CustomerName(message ="Wrong format name")
    private String name;
    @Column(name="Gender")
    @Gender(message="Male or Female or Other")
    private String gender;
    @Column(name="DateOfBirth")
@CustomerDateOfBirth(message="Need 18 years old")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @NotBlank(message="Address is not blank")
    @Column(name="Address")
    private String address;
    @OneToOne
    @JoinColumn(name = "AccountID")
    private Account account;
    @Column(name="Age")
    @Min(18)
    private Integer age;

}
