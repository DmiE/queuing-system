package app.service;

import app.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void save(User userMaria, Boolean isAdmin);
    User findByEmail(String username);
    List<User> findAll();
    User findById(Long userID);
    @Transactional
    void deleteUser(String email);
}
