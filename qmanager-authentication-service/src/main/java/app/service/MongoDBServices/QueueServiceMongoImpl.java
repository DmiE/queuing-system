package app.service.MongoDBServices;

import app.entity.MongoEntities.QueueRowMongoDB;
import app.entity.MongoEntities.UserMongoDB;
import app.entity.QueueRow;
import app.exceptions.AppException;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.repository.MongoRepositories.MongoDBQueueRepository;
import app.service.MariaDBServices.QueueServiceMariaImpl;
import app.service.QueueService;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class QueueServiceMongoImpl implements QueueService {

    private MongoDBQueueRepository queueRepository;
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(QueueServiceMariaImpl.class);

    @Autowired
    public QueueServiceMongoImpl(MongoDBQueueRepository queueRepository, UserServiceMongoImpl userService) {
        this.queueRepository = queueRepository;
        this.userService = userService;
    }

    @Override
    public void addUserToQueue(String  queueName, String email) {
        UserMongoDB user = new UserMongoDB(userService.findByEmail(email));
        if(! queueRepository.existsByQueueName(queueName)) {
            throw new ResourceNotFoundException(String.format("Queue with name %s does not exists", queueName));
        }
        if( queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceAlreadyExistsException(String.format("User with email %s already exist in queue", email));
        }
        Date currentTime = Timestamp.valueOf(LocalDateTime.now());
        queueRepository.save(new QueueRowMongoDB(queueName, user, currentTime ,false));
    }

    @Override
    public List<QueueRow>getQueue(String queueName){
        if (! queueRepository.existsByQueueName(queueName)){
            throw  new ResourceNotFoundException(String.format("Queue with name: %s does not exists",queueName));
        }
        List<QueueRowMongoDB> queueRows = queueRepository.findByQueueNameAndFinishedOrderByCreatedAtAsc(queueName, false);
        List<QueueRow> rows = new ArrayList<>();
        queueRows.forEach(queueRow -> rows.add(new QueueRow(queueRow)));
        return rows;
    }

    @Override
    public List<QueueRow> getAllQueues() {
        List<QueueRowMongoDB> queueRows = queueRepository.findByFinishedOrderByQueueName(false);
        List<QueueRow> rows = new ArrayList<>();
        queueRows.forEach(queueRow -> rows.add(new QueueRow(queueRow)));
        return rows;
    }


    @Override
    public void createQueue(String queueName, String email) {
        UserMongoDB user = new UserMongoDB(userService.findByEmail(email));
        if ( queueRepository.existsByQueueName(queueName)){
            throw  new ResourceAlreadyExistsException(String.format("Queue with name: %s already exists",queueName));
        }
        Date currentTime = Timestamp.valueOf(LocalDateTime.now());
        queueRepository.save(new QueueRowMongoDB(queueName, user, currentTime ,true));
    }

    @Override
    public Set<String> getQueueNames(){
        List<QueueRowMongoDB> rows = queueRepository.findAll();
        Set<String> queueNames = new HashSet<>();
        rows.forEach(queueRowMariaDB -> queueNames.add(queueRowMariaDB.getQueueName()));
        return queueNames;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void deleteUserFromQueue(String email, String queueName){
        UserMongoDB user = new UserMongoDB(userService.findByEmail(email));
        if(! queueRepository.existsByQueueName(queueName)) {
            throw new ResourceNotFoundException(String.format("Queue with name %s does not exists", queueName));
        }
        if(! queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceNotFoundException(String.format("User with id %s does not exists ", user.getId()));
        }
        List<QueueRowMongoDB> deleted= queueRepository.deleteByUserAndQueueName(user, queueName);
        if( deleted.size() ==0 ){
            throw new AppException("User not deleted ");
        }
    }
    @Override
    public void deleteUserFromQueue(String email){
        UserMongoDB user = new UserMongoDB(userService.findByEmail(email));
        if(! queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceNotFoundException(String.format("User with id %s does not exists ", user.getId()));
        }
        List<QueueRowMongoDB> deleted= queueRepository.deleteByUser(user);
        if( deleted.size() ==0 ){
            throw new AppException("User not deleted ");
        }
    }
}
