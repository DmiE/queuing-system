package app.repository.MariaRepositories;

import app.entity.MariaEntities.UserMariaDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MariaDBUserRepository extends CrudRepository<UserMariaDB, Long> {
    Optional<UserMariaDB> findByEmail(String email);
    Optional<UserMariaDB> findByLastName(String lastName);
    Boolean existsByEmail(String email);
    List<UserMariaDB> findByIdIn(List<Long> userIds);
//
//    @Query("DELETE FROM users u WHERE u.email = :email")
    void deleteById(Long  id);
}