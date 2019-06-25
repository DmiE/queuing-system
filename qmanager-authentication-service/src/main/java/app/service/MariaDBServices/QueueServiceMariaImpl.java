package app.service.MariaDBServices;
import app.entity.MariaEntities.QueueRowMariaDB;
import app.entity.MariaEntities.UserMariaDB;
import app.entity.QueueRow;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        User user2 = userService.findByEmail(email);
        UserMariaDB user = new UserMariaDB(user2);
        if(! queueRepository.existsByQueueName(queueName)) {
            throw new ResourceNotFoundException(String.format("Queue with name %s does not exists", queueName));
        }
        if( queueRepository.existsByUserAndFinished(user, false)){
            throw new ResourceAlreadyExistsException(String.format("User with email %s already exist in queue", email));
        }
        queueRepository.save(new QueueRowMariaDB(queueName, user));
    }

    @Override
    public List<QueueRow>getQueue(String queueName){
        if (! queueRepository.existsByQueueName(queueName)){
            throw  new ResourceNotFoundException(String.format("Queue with name: %s does not exists",queueName));
        }
        List<QueueRowMariaDB> queueRows = queueRepository.findByQueueNameAndFinishedOrderByCreatedAtAsc(queueName, false);
        List<QueueRow> rows = new ArrayList<>();
        queueRows.forEach(queueRow -> rows.add(new QueueRow(queueRow)));
        return rows;
    }

    @Override
    public List<QueueRow> getAllQueues() {
        List<QueueRowMariaDB> queueRows = queueRepository.findByFinishedOrderByQueueName(false);
        List<QueueRow> rows = new ArrayList<>();
        queueRows.forEach(queueRow -> rows.add(new QueueRow(queueRow)));
        return rows;
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
            throw new AppException("User not deleted ");
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
            throw new AppException("User not deleted ");
        }
    }

    @Override
    public Set<String> getQueueNames(){
        List<QueueRowMariaDB> rows = queueRepository.findAll();
        Set <String> queueNames = new HashSet<>();
        rows.forEach(queueRowMariaDB -> queueNames.add(queueRowMariaDB.getQueueName()));
        return queueNames;
    }
}
