package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import fa.training.phonestore.entity.Role;
@Entity
@Data
@Table(name = "Account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
@Id
@Column(name = "AccountID")
    private int accountId;
@Column(name = "Username")
private String username;
@Column(name = "Password")
private String password;
@ManyToOne
@JoinColumn(name = "RoleID")
private Role role;
@Column(name = "is_active")
private boolean status;
}
