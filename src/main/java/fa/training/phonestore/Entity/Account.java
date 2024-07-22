package fa.training.phonestore.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fa.training.phonestore.Constraint.EntityConstraint.DtoConstraint.Password;
import fa.training.phonestore.Constraint.EntityConstraint.DtoConstraint.Username;
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

    @PrePersist
    public void prePersist() {
        if (role == null) {
            Role defaultRole = new Role();
            defaultRole.setRoleId(3); // Thiết lập RoleID mặc định là 3
            this.role = defaultRole;
        }
        setActive(true);
    }
}
