package app.repository.MariaRepositories;

import app.entity.MariaEntities.RoleMariaDB;
import app.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MariaDBRoleRepository extends JpaRepository<RoleMariaDB, Long> {
    Optional<RoleMariaDB> findByName(RoleName roleName);
}
