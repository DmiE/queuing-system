package app.service.MongoDBServices;

import app.entity.MongoEntities.UserMongoDB;
import app.entity.User;
import app.repository.MongoRepositories.MongoDBUserRepository;
import app.service.UserDetailsServiceIf;
import app.service.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceMongoImpl implements UserDetailsServiceIf {
    private MongoDBUserRepository userRepository;

    @Autowired
    public UserDetailsServiceMongoImpl(MongoDBUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        UserMongoDB user = userRepository.findByEmail(usernameOrEmail).
                orElseThrow(() ->new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
        return UserPrincipal.create(new User(user));
    }

    @Override
    @Transactional
    public UserDetails loadUserByEmail(String email)
            throws UsernameNotFoundException {
        UserMongoDB user = userRepository.findByEmail(email).
                orElseThrow(() ->new UsernameNotFoundException("User not found with username or email : " + email));
        return UserPrincipal.create(new User(user));
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserMongoDB user = userRepository.findById(id.toString()).
                orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
        return UserPrincipal.create(new User(user));
    }

}
