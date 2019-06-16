package app.entity;

import app.entity.Audit.DateAudit;
import app.payload.LoginRequest;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "queue", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "id"
        })
})
public class Queue extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column(name="ququeID")
    private Long ququeID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="finished")
    private Boolean finished;

    public Queue(Long ququeID, User user, Boolean finished){
        this.ququeID = ququeID;
        this.user = user;
        this.finished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuqueID() {
        return ququeID;
    }

    public void setQuqueID(Long ququeID) {
        this.ququeID = ququeID;
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