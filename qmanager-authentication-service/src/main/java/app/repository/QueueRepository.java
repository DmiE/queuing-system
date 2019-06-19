package app.repository;

import app.entity.QueueRow;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface QueueRepository extends JpaRepository<QueueRow, Long> {
    Boolean existsByUserAndFinished(User user, Boolean isFinished);
    Boolean existsByQueueName(String queueName);
    List<QueueRow> findByQueueNameAndFinishedOrderByCreatedAtAsc(String  queueName, Boolean isfinished);
    List<QueueRow> findByFinishedOrderByQueueName(Boolean finished);
    List<QueueRow> deleteByUserAndQueueName(User user, String queueName);
    List<QueueRow> deleteByUser(User user);
    //    @Query("DELETE FROM table_name WHERE condition")
//    Boolean deleteFromQueueByUserNameAndQueueName(String userName, String queueName);
}