package app.entity;

import java.util.HashSet;
import java.util.Set;

public abstract class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private Set<Role> roles = new HashSet<>();

    protected User(String first_name, String last_name, String email, String password) {
        this.firstName = last_name;
        this.email = email;
        this.password = password;
        this.lastName = first_name;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
