package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fa.training.phonestore.Constraint.EntityConstraint.dtoconstraint.Password;
import fa.training.phonestore.Constraint.EntityConstraint.dtoconstraint.Username;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
    @Id
    @Column(name = "AccountID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int accountId;
    @Column(name = "Username")
@Username
    private String username;
    @Column(name = "Password")
    @Password
    private String password;
    @ManyToOne
    @JoinColumn(name = "RoleID")
    private Role role;

    @Column(name = "IsActive")
        private boolean isActive;

}
