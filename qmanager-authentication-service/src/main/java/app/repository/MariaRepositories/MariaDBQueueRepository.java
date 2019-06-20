package app.repository.MariaRepositories;

import app.entity.QueueRow;
import app.entity.MariaEntities.UserMaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MariaDBQueueRepository  extends JpaRepository<QueueRow, Long> {
    Boolean existsByUserAndFinished(UserMaria userMaria, Boolean isFinished);
    Boolean existsByQueueName(String queueName);
    List<QueueRow> findByQueueNameAndFinishedOrderByCreatedAtAsc(String  queueName, Boolean isfinished);
    List<QueueRow> findByFinishedOrderByQueueName(Boolean finished);
    List<QueueRow> deleteByUserAndQueueName(UserMaria userMaria, String queueName);
    List<QueueRow> deleteByUser(UserMaria userMaria);
}