package fa.training.phonestore.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fa.training.phonestore.Entity.EntityConstraint.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.lang.annotation.Annotation;

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
