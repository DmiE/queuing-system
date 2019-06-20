package app.service;

import app.entity.MariaEntities.QueueRowMariaDB;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QueueService {
    void addUserToQueue(String queueName, String email);
    void  createQueue(String queueName,String userID);
    List<QueueRowMariaDB> getQueue(String queueName);
    List<QueueRowMariaDB> getAllQueues();
    @Transactional
    void deleteUserFromQueue(String userEmail, String queueName);
    @Transactional
    void deleteUserFromQueue(String userEmail);
}
