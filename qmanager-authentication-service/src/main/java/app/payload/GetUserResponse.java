package app.payload;


import app.entity.User;

public class GetUserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public GetUserResponse (User user){
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName;}

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

}
