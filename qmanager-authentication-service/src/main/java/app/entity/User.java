package app.entity;

import app.entity.MariaEntities.RoleMariaDB;
import app.entity.MariaEntities.UserMariaDB;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleMariaDB> roles = new HashSet<>();

    public User(String first_name, String last_name, String email, String password) {
        this.firstName = last_name;
        this.email = email;
        this.password = password;
        this.lastName = first_name;
    }

    public User(UserMariaDB user) {
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.lastName = user.getFirstName();
        this.id = user.getId();
        this.roles = user.getRoles();
    }

    protected User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Set<RoleMariaDB> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleMariaDB> roles) {
        this.roles = roles;
    }
}
