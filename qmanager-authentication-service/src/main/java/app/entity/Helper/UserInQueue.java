package app.entity.Helper;

import app.entity.MariaEntities.QueueRowMariaDB;
import java.time.Instant;

public class UserInQueue {

    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private Instant createdAt;
    
    public UserInQueue(QueueRowMariaDB row){
        this.userID = row.getUser().getId();
        this.firstName = row.getUser().getFirstName();
        this.lastName = row.getUser().getLastName();
        this.email = row.getUser().getEmail();
        this.createdAt = row.getCreatedAt();    
    }

    public Long getId() { return userID; }

    public void setId(Long id) { this.userID = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email; }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }


}

