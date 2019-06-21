package app.service;

import app.entity.QueueRow;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QueueService {
    void addUserToQueue(String queueName, String email);
    void  createQueue(String queueName,String userID);
    List<QueueRow> getQueue(String queueName);
    List<QueueRow> getAllQueues();
    @Transactional
    void deleteUserFromQueue(String userEmail, String queueName);
    @Transactional
    void deleteUserFromQueue(String userEmail);
}
