package app.service;

import app.entity.User;

import java.util.List;

public interface UserService {
    void save(User user, Boolean isAdmin);
    User findByEmail(String username);
    List<User> findAll();
    User findById(Long userID);
}
