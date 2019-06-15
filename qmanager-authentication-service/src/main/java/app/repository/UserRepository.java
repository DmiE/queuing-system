package app.repository;


import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByLastName(String lastName);
    Boolean existsByEmail(String email);
    List<User> findByIdIn(List<Long> userIds);
}