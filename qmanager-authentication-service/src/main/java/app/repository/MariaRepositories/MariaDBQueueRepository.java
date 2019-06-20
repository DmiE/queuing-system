package app.repository.MariaRepositories;

import app.entity.MariaEntities.QueueRowMariaDB;
import app.entity.MariaEntities.UserMariaDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MariaDBQueueRepository  extends JpaRepository<QueueRowMariaDB, Long> {
    Boolean existsByUserAndFinished(UserMariaDB userMaria, Boolean isFinished);
    Boolean existsByQueueName(String queueName);
    List<QueueRowMariaDB> findByQueueNameAndFinishedOrderByCreatedAtAsc(String  queueName, Boolean isFinished);
    List<QueueRowMariaDB> findByFinishedOrderByQueueName(Boolean finished);
    List<QueueRowMariaDB> deleteByUserAndQueueName(UserMariaDB userMaria, String queueName);
    List<QueueRowMariaDB> deleteByUser(UserMariaDB userMaria);
}