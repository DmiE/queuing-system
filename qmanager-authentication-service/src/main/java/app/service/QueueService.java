package app.service;

import app.entity.QueueRow;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface QueueService {
    void addUserToQueue(String queueName, String email);
    void  createQueue(String queueName,String userID);
    List<QueueRow> getQueue(String queueName);
    List<QueueRow> getAllQueues();
    @Transactional
    void deleteUserFromQueue(String userEmail, String queueName);
    @Transactional
    void deleteUserFromQueue(String userEmail);
    Set<String> getQueueNames();
}
