package app.entity;

import app.entity.Audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name = "queues", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "id"
        })
})
public class QueueRow extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="quque_name")
    private String queueName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Column(name="finished")
    private Boolean finished;

    public QueueRow(){
    }

    public QueueRow(String name, User user){
        super();
        this.queueName = name;
        this.user = user;
        this.finished = false;
    }

    public QueueRow(String name, User user, Boolean is_finished ) {
        super();
        this.queueName = name;
        this.user = user;
        this.finished = is_finished;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getQueueName() { return queueName; }

    public void setQueueName(String queueName) { this.queueName = queueName; }
}