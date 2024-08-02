package QuizApplication.Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String username;
//    @Column(name ="full_name",nullable = false)
//    private String fullName;
    @Column(name = "pw", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    // Utility method to add role
    public void addRole(Role role) {
        roles.add(role);
        role.setUser(this);
    }
}
