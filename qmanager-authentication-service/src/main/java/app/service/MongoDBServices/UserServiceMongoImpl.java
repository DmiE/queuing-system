package app.service.MongoDBServices;

import app.entity.MongoEntities.UserMongoDB;
import app.entity.RoleName;
import app.entity.User;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.repository.MongoRepositories.MongoDBQueueRepository;
import app.repository.MongoRepositories.MongoDBUserRepository;
import app.service.QueueService;
import app.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceMongoImpl implements UserService {

    private MongoDBUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MongoDBQueueRepository queueRepository;

    @Autowired
    public UserServiceMongoImpl(MongoDBUserRepository userRepository,
                                PasswordEncoder passwordEncoder,
                                MongoDBQueueRepository queueRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.queueRepository = queueRepository;
    }

    @Override
    public void save(User u, Boolean isAdmin) {
        UserMongoDB user = new UserMongoDB(u);
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ResourceAlreadyExistsException(String.format("User with email %s already exists", user.getEmail()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Collections.singleton(setUserRole(isAdmin)));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        UserMongoDB user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s does not exists", email)));
        return new User(user);
    }

    @Override
    public User findById(Long userID) {
        UserMongoDB user = userRepository.findById(userID.toString())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s does not exists", userID)));
        return new User(user);
    }

    @Override
    public void deleteUser(String email) {
        User user = findByEmail(email);
        queueRepository.deleteByUser(new UserMongoDB(findByEmail(email)));
        userRepository.deleteById(user.getId());
    }

    @Override
    public List<User> findAll() {
        List<UserMongoDB> usersMongo = userRepository.findAll();
        List<User> users = new ArrayList<>();
        usersMongo.forEach(userMongo -> users.add(new User(userMongo)));
        return users;
    }

    private RoleName setUserRole(Boolean isAdmin){
        if (isAdmin){
            return RoleName.ROLE_ADMIN;
        }
        else {
            return RoleName.ROLE_USER;
        }
    }

}
