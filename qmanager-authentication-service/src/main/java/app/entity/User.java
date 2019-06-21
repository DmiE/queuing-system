package app.entity;

import app.entity.MariaEntities.RoleMariaDB;
import app.entity.MariaEntities.UserMariaDB;
import app.entity.MongoEntities.UserMongoDB;
import app.utils.Mapper;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleName> roles = new HashSet<>();

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
        this.lastName = user.getLastName();
        this.id = user.getId().toString();
        this.roles = Mapper.mapSetRoleMariaDBtoSetRoleName(user.getRoles());
    }

    public User(UserMongoDB user) {
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.lastName = user.getLastName();
        this.id = user.getId();
        this.roles = user.getRole();
    }

    protected User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }
}
