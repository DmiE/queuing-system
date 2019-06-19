package app.service;

import app.entity.QueueRow;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QueueService {
    void addUserToQueue(String queueName, Long userID);
    void  createQueue(String queueName,Long userID);
    List<QueueRow> getQueue(String queueName);
    List<QueueRow> getAllQueues();
    @Transactional
    void deleteUserFromQueue(String userEmail, String queueName);
    @Transactional
    void deleteUserFromQueue(String userEmail);
}
