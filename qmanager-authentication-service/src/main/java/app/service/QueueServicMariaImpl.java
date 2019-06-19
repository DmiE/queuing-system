package app.service;

import app.entity.QueueRow;
import app.entity.User;
import app.exceptions.AppException;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void addUserToQueue(String  queueName, Long userID) {
        User user = userService.findById(userID);
        if(! queueRepository.existsByQueueName(queueName)) {
            throw new ResourceNotFoundException(String.format("Queue with name %s does not exists", queueName));
        }
        if( queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceAlreadyExistsException(String.format("User with id %s already exist in queue", userID));
        }
        queueRepository.save(new QueueRow(queueName, user));
    }

    @Override
    public List<QueueRow>getQueue(String queueName){
        if (! queueRepository.existsByQueueName(queueName)){
            throw  new ResourceNotFoundException(String.format("Queue with name: %s does not exists",queueName));
        }
        return  queueRepository.findByQueueNameAndFinishedOrderByCreatedAtAsc(queueName, false);
    }

    @Override
    public List<QueueRow> getAllQueues() {
        return  queueRepository.findByFinishedOrderByQueueName(false);
    }


    @Override
    public void createQueue(String queueName, Long userID) {
        User user = userService.findById(userID);
        if ( queueRepository.existsByQueueName(queueName)){
            throw  new ResourceAlreadyExistsException(String.format("Queue with name: %s already exists",queueName));
        }
        queueRepository.save(new QueueRow(queueName, user, true));
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void deleteUserFromQueue(String email, String queueName){
        User user = userService.findByEmail(email);
        if(! queueRepository.existsByQueueName(queueName)) {
            throw new ResourceNotFoundException(String.format("Queue with name %s does not exists", queueName));
        }
        if(! queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceNotFoundException(String.format("User with id %d does not exists ", user.getId()));
        }
        List<QueueRow> deleted= queueRepository.deleteByUserAndQueueName(user, queueName);
        if( deleted.size() ==0 ){
            throw new AppException("User not deleted ");
        }
    }
    @Override
    public void deleteUserFromQueue(String email){
        User user = userService.findByEmail(email);
        if(! queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceNotFoundException(String.format("User with id %d does not exists ", user.getId()));
        }
        List<QueueRow> deleted= queueRepository.deleteByUser(user);
        if( deleted.size() ==0 ){
            throw new AppException("User not deleted ");
        }
    }

}
