package app.entity.MariaEntities;
import app.entity.Audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name = "queues", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "id"
        })
})
public class QueueRowMariaDB extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="quque_name")
    private String queueName;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserMariaDB user;

    @Column(name="finished")
    private Boolean finished;
    public QueueRowMariaDB(){
    }

    public QueueRowMariaDB(String name, UserMariaDB user){
        super();
        this.queueName = name;
        this.user = user;
        this.finished = false;
    }

    public QueueRowMariaDB(String name, UserMariaDB user, Boolean isFinished ) {
        super();
        this.queueName = name;
        this.user = user;
        this.finished = isFinished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserMariaDB getUser() {
        return user;
    }

    public void setUser(UserMariaDB user) {
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