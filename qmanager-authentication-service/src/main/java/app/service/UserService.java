package app.service;

import app.entity.User;

import java.util.List;

public interface UserService {
    Boolean save(User user, Boolean isAdmin);
    User findByEmail(String username);
    List<User> findAll();
}
