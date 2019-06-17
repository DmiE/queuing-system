package app.repository;

import app.entity.QueueRow;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface QueueRepository extends JpaRepository<QueueRow, Long> {
    Boolean existsByQueueNameAndUserAndFinished(String  Name, User user, Boolean isFinished);
    Boolean existsByQueueName(String queueName);
    List<QueueRow> findByQueueNameAndFinishedOrderByCreatedAtAsc(String  queueName, Boolean isfinished);
}