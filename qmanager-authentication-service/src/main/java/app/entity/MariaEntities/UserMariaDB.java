package app.entity.MariaEntities;
import app.entity.User;
import app.service.MariaDBServices.QueueServiceMariaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})

public class UserMariaDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleMariaDB> roles = new HashSet<>();

    private static final Logger logger = LoggerFactory.getLogger(QueueServiceMariaImpl.class);

    public UserMariaDB(String first_name, String last_name, String email, String password) {
        this.firstName = last_name;
        this.email = email;
        this.password = password;
        this.lastName = first_name;
    }

    public UserMariaDB(User user) {
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.lastName = user.getLastName();
        if ( user.getId() != null){
            this.id = Long.parseLong(user.getId());
        }
    }

    public UserMariaDB() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleMariaDB> getRoles() { return roles; }

    public void setRoles(Set<RoleMariaDB> roles) { this.roles = roles;}

}