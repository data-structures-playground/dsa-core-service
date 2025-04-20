package com.dsa.core.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dsa.core.model.User;
import com.dsa.core.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { User user =
	 * userRepository.findByUsername(username) .orElseThrow(() -> new
	 * UsernameNotFoundException("User not found with username: " + username));
	 * 
	 * // For now, we'll assign a default role. In a real application, this would
	 * come from the database. return new
	 * org.springframework.security.core.userdetails.User( user.getUsername(),
	 * user.getPassword(), Collections.singletonList(new
	 * SimpleGrantedAuthority("ROLE_USER")) ); }
	 */
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Attempting to load user: {}", username);
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

            log.info("User found: {}", user.getUsername());
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );
        } catch (Exception e) {
            log.error("Error loading user {}", username, e);
            throw e; // Re-throw the exception
        }
    }
}
