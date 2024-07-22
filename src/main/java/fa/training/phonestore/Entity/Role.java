// Role.java
package fa.training.phonestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Role {
    @Id
    @Column(name = "RoleID")
    private int RoleId;
    @Column(name = "role_name")
    private String roleName;
}