package app.service;

import app.entity.QueueRow;
import app.entity.User;

import java.util.List;

public interface QueueService {
    void addUserToQueue(String queueName, Long userID);
    void  createQueue(String queueName,Long userID);
    List<QueueRow> getQueue(String queueName);
}
