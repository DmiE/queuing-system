package app.service;

import app.entity.User;

public interface QueueService {
    Boolean addUserToQueue(String queueName, Long userID);
    Boolean createQueue(String queueName,Long userID);
}
