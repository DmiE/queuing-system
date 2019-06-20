package app.service;

import app.entity.Role;
import app.entity.RoleName;
import app.entity.MariaEntities.UserMaria;
import app.entity.User;
import app.exceptions.AppException;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class UserServiceMariaImpl implements  UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private  PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceMariaImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user, Boolean isAdmin) {

        if(userRepository.existsByEmail(user.getEmail())){
            throw new ResourceAlreadyExistsException(String.format("User with email %s already exists", user.getEmail()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(setUserRole(isAdmin)));
        userRepository.save(new UserMaria(user));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s does not exists", email)));
    }

    @Override
    public UserMaria findById(Long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s does not exists", userID)));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userRepository.findAll());
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
