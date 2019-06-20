package app.repository;


import app.entity.MariaEntities.UserMaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserMaria, Long> {
    Optional<UserMaria> findByEmail(String email);
    Optional<UserMaria> findByLastName(String lastName);
    Boolean existsByEmail(String email);
    List<UserMaria> findByIdIn(List<Long> userIds);
}