package app.repository.MongoRepositories;

import app.entity.MariaEntities.QueueRowMariaDB;
import app.entity.MongoEntities.QueueRowMongoDB;
import app.entity.MongoEntities.UserMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoDBQueueRepository extends MongoRepository<QueueRowMongoDB, String> {
    Boolean existsByUserAndFinished(UserMongoDB userMongoDB, Boolean isFinished);
    Boolean existsByQueueName(String queueName);
    List<QueueRowMongoDB> findByQueueNameAndFinishedOrderByCreatedAtAsc(String  queueName, Boolean isFinished);
    List<QueueRowMongoDB> findByFinishedOrderByQueueName(Boolean finished);
    List<QueueRowMongoDB> deleteByUserAndQueueName(UserMongoDB userMongoDB, String queueName);
    List<QueueRowMongoDB> deleteByUser(UserMongoDB userMongoDB);
    List<QueueRowMongoDB> findAll();
}
