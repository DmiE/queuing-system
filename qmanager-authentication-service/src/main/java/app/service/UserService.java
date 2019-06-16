package app.service;

import app.entity.User;

import java.util.List;

public interface UserService {
    Boolean save(User user);
    User findByEmail(String username);

    default List<User> findAll() {
        return null;
    }
}
