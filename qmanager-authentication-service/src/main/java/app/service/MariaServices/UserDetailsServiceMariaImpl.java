package app.service.MariaServices;

import app.entity.MariaEntities.UserMariaDB;
import app.entity.User;
import app.repository.MariaRepositories.MariaDBUserRepository;
import app.service.UserDetailsServiceIf;
import app.service.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceMariaImpl implements UserDetailsServiceIf {
    private MariaDBUserRepository userRepository;

    @Autowired
    public UserDetailsServiceMariaImpl(MariaDBUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        UserMariaDB userMaria = userRepository.findByEmail(usernameOrEmail).
                orElseThrow(() ->new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
        return UserPrincipal.create(new User(userMaria));
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserMariaDB userMaria = userRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
        return UserPrincipal.create(new User(userMaria));
    }
}