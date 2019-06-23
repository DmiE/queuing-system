package app.repository.MariaRepositories;

import app.entity.MariaEntities.UserMariaDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MariaDBUserRepository extends JpaRepository<UserMariaDB, Long> {
    Optional<UserMariaDB> findByEmail(String email);
    Optional<UserMariaDB> findByLastName(String lastName);
    Boolean existsByEmail(String email);
    List<UserMariaDB> findByIdIn(List<Long> userIds);
    List<UserMariaDB> deleteByEmail(String email);
}