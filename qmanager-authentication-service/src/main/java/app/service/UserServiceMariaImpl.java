package app.service;

import app.entity.Role;
import app.entity.RoleName;
import app.entity.User;
import app.exceptions.AppException;
import app.payload.ApiResponse;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import app.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public Boolean save(User user) {

        if(userRepository.existsByEmail(user.getEmail())){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByEmail(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
