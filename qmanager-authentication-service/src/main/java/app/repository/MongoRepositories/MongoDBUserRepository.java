package app.repository.MongoRepositories;

import app.entity.MongoEntities.UserMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoDBUserRepository extends MongoRepository<UserMongoDB, String> {
    Optional<UserMongoDB> findByEmail(String email);
    Optional<UserMongoDB> findByLastName(String lastName);
    Boolean existsByEmail(String email);
    List<UserMongoDB> findByIdIn(List<Long> userIds);

}
