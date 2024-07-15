package fa.training.phonestore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private Integer employeeId;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "FullName", length = 100)
    private String fullName;

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "Gender", length = 10)
    private String gender;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "AccountID")
    private Integer accountId;

    @Column(name = "AgencyID")
    private Integer agencyId;

    @ManyToOne
    @JoinColumn(name = "AccountID",insertable=false, updatable=false)
    private Account account;

}

