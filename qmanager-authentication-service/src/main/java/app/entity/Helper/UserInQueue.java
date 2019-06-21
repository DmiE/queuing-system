package app.entity.Helper;

import app.entity.QueueRow;

import java.util.Date;

public class UserInQueue {

    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    
    public UserInQueue(QueueRow row){
        this.userID = row.getUser().getId();
        this.firstName = row.getUser().getFirstName();
        this.lastName = row.getUser().getLastName();
        this.email = row.getUser().getEmail();
        this.createdAt = row.getCreatedAt();    
    }

    public String getId() { return userID; }

    public void setId(String id) { this.userID = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }


}

