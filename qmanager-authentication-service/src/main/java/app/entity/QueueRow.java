package app.entity;

import app.entity.MariaEntities.QueueRowMariaDB;
import app.entity.MongoEntities.QueueRowMongoDB;

import java.util.Date;

public class QueueRow {
    private String id;
    private String queueName;
    private User user;
    private Boolean finished;
    private Date createdAt;

    public QueueRow(){ }
    public QueueRow(QueueRowMongoDB row ){
        this.id = row.getId();
        this.queueName = row.getQueueName();
        this.finished = row.getFinished();
        this.user = new User(row.getUser());
        this.createdAt = row.getCreatedAt();
    }
    public QueueRow(QueueRowMariaDB row ){
        this.id = row.getId().toString();
        this.queueName = row.getQueueName();
        this.finished = row.getFinished();
        this.user = new User(row.getUser());
        this.createdAt = row.getCreatedAt();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
