package app.entity.MongoEntities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "queues")
public class QueueRowMongoDB {

    @Id
    private String id;

    private String queueName;

    private UserMongoDB user;

    private Boolean finished;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public QueueRowMongoDB(){
    }

    public QueueRowMongoDB(String name, UserMongoDB user){
        this.queueName = name;
        this.user = user;
        this.finished = false;
    }

    public QueueRowMongoDB(String name, UserMongoDB user, Date createdAt, Boolean isFinished ) {
        this.queueName = name;
        this.user = user;
        this.finished = isFinished;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public UserMongoDB getUser() {
        return user;
    }

    public void setUser(UserMongoDB user) {
        this.user = user;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
