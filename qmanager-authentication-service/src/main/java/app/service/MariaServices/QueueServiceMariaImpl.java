package app.service.MariaServices;
import app.controller.UserController;
import app.entity.MariaEntities.QueueRowMariaDB;
import app.entity.MariaEntities.UserMariaDB;
import app.entity.User;
import app.exceptions.AppException;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.repository.MariaRepositories.MariaDBQueueRepository;
import app.service.QueueService;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueServiceMariaImpl implements QueueService {
    private MariaDBQueueRepository queueRepository;
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(QueueServiceMariaImpl.class);

    @Autowired
    public QueueServiceMariaImpl(MariaDBQueueRepository queueRepository, UserServiceMariaImpl userService) {
        this.queueRepository = queueRepository;
        this.userService = userService;
    }

    @Override
    public void addUserToQueue(String  queueName, String email) {
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.DEBUG);
        User user2 = userService.findByEmail(email);
        logger.error(String.format("DEBUG MICHAL2 user2 %S", user2.getId()));
        UserMariaDB user = new UserMariaDB(user2);
        logger.error(String.format("DEBUG MICHAL user %S", user.getId()));
        System.out.println("DUPA"+ user.getId());
        if(! queueRepository.existsByQueueName(queueName)) {
            throw new ResourceNotFoundException(String.format("Queue with name %s does not exists", queueName));
        }
        if( queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceAlreadyExistsException(String.format("User with email %s already exist in queue", email));
        }
        queueRepository.save(new QueueRowMariaDB(queueName, user));
    }

    @Override
    public List<QueueRowMariaDB>getQueue(String queueName){
        if (! queueRepository.existsByQueueName(queueName)){
            throw  new ResourceNotFoundException(String.format("Queue with name: %s does not exists",queueName));
        }
        return  queueRepository.findByQueueNameAndFinishedOrderByCreatedAtAsc(queueName, false);
    }

    @Override
    public List<QueueRowMariaDB> getAllQueues() {
        return  queueRepository.findByFinishedOrderByQueueName(false);
    }


    @Override
    public void createQueue(String queueName, String email) {
        UserMariaDB user = new UserMariaDB(userService.findByEmail(email));
        if ( queueRepository.existsByQueueName(queueName)){
            throw  new ResourceAlreadyExistsException(String.format("Queue with name: %s already exists",queueName));
        }
        queueRepository.save(new QueueRowMariaDB(queueName, user, true));
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void deleteUserFromQueue(String email, String queueName){
        UserMariaDB user = new UserMariaDB(userService.findByEmail(email));
        if(! queueRepository.existsByQueueName(queueName)) {
            throw new ResourceNotFoundException(String.format("Queue with name %s does not exists", queueName));
        }
        if(! queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceNotFoundException(String.format("User with id %d does not exists ", user.getId()));
        }
        List<QueueRowMariaDB> deleted= queueRepository.deleteByUserAndQueueName(user, queueName);
        if( deleted.size() ==0 ){
            throw new AppException("UserMariaDB not deleted ");
        }
    }
    @Override
    public void deleteUserFromQueue(String email){
        UserMariaDB user = new UserMariaDB(userService.findByEmail(email));
        if(! queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceNotFoundException(String.format("User with id %d does not exists ", user.getId()));
        }
        List<QueueRowMariaDB> deleted= queueRepository.deleteByUser(user);
        if( deleted.size() ==0 ){
            throw new AppException("UserMariaDB not deleted ");
        }
    }

}
