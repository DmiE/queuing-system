package app.entity.MongoEntities;

import app.entity.RoleName;
import app.entity.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(collection = "users")
public class UserMongoDB {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private Set<RoleName> role;

    public UserMongoDB(String first_name, String last_name, String email, String password) {
        this.firstName = last_name;
        this.email = email;
        this.password = password;
        this.lastName = first_name;
    }

    public UserMongoDB(User user) {
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.lastName = user.getFirstName();
        if ( user.getId() != null){
            this.id = user.getId().toString();
        }
    }

    public UserMongoDB() { }

    public Set<RoleName> getRole() {
        return role;
    }


    public void setRole(Set<RoleName> role) {
        this.role = role;
    }

    public String getId() { return id; }

    public void setId(String  id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}


