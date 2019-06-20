package app.service.MariaServices;

import app.entity.Role;
import app.entity.RoleName;
import app.entity.MariaEntities.UserMaria;
import app.entity.User;
import app.exceptions.AppException;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.repository.MariaRepositories.MariaDBRoleRepository;
import app.repository.MariaRepositories.MariaDBUserRepository;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;


@Service
public class UserServiceMariaImpl implements UserService {
    private MariaDBUserRepository userRepository;
    private MariaDBRoleRepository roleRepository;
    private  PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceMariaImpl(MariaDBUserRepository userRepository, MariaDBRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User u, Boolean isAdmin) {
        UserMaria user = new UserMaria(u);
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ResourceAlreadyExistsException(String.format("User with email %s already exists", user.getEmail()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(setUserRole(isAdmin)));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        UserMaria user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s does not exists", email)));
        return new User(user);
    }

    @Override
    public User findById(Long userID) {
        UserMaria user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s does not exists", userID)));
        return new User(user);
    }

    @Override
    public List<User> findAll() {
        List<UserMaria> usersMaria = userRepository.findAll();
        List<User> users = new ArrayList<>();
        usersMaria.forEach(userMaria -> users.add(new User(userMaria)));
        return users;
    }

    private Role setUserRole(Boolean isAdmin){
        Role userRole;
        if (isAdmin){
            userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException("User Role not set."));
        }
        else {
            userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("User Role not set."));
        }

        return userRole;
    }

}
