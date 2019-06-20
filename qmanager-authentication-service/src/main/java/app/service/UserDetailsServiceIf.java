package app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceIf extends UserDetailsService {
    UserDetails loadUserByUsername(String usernameOrEmail);
    UserDetails loadUserById(Long id);
}
