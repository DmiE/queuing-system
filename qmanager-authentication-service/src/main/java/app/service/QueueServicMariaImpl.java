package app.service;

import app.entity.QueueRow;
import app.entity.User;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueServicMariaImpl implements QueueService {
    private QueueRepository queueRepository;
    private UserService userService;

    @Autowired
    public QueueServicMariaImpl(QueueRepository queueRepository, UserServiceMariaImpl userService) {
        this.queueRepository = queueRepository;
        this.userService = userService;
    }

    @Override
    public Boolean addUserToQueue(String  queueName, Long userID) {
        User user = userService.findById(userID);
        if(queueRepository.existsByQueueName(queueName)) {
            throw new ResourceNotFoundException(String.format("Queue with name %s does not exists", queueName));
        }
        if(! queueRepository.existsByQueueNameAndUserAndFinished(queueName, user, true)){
            throw new ResourceAlreadyExistsException(String.format("User with id %s already exist in queue with name %s", userID, queueName));
        }
        queueRepository.save(new QueueRow(queueName, user));
        return true;
    }

    @Override
    public Boolean createQueue(String queueName, Long userID) {
        User user = userService.findById(userID);
        if (queueRepository.existsByQueueName(queueName)){
            throw  new ResourceAlreadyExistsException(String.format("Queue with name: %s already exists",queueName));
        }
        queueRepository.save(new QueueRow(queueName, user, true));
        return true;
    }
}
