package app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceIf extends UserDetailsService {
    UserDetails loadUserByEmail(String email);
    UserDetails loadUserById(Long id);
}
