package app.payload;


import app.entity.RoleName;
import app.entity.User;

import java.util.Set;

public class GetUserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<RoleName> role;

    public GetUserResponse (User user){
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        role = user.getRoles();
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName;}

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Set<RoleName> getRole() {
        return role;
    }

    public void setRole(Set<RoleName>  role) {
        this.role = role;
    }
}
