package app.service;

import app.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findByEmail(String username);
    List<User> findAll();
}
