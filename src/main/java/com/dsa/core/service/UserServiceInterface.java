package com.dsa.core.service;

import java.util.Optional;

import com.dsa.core.dto.RegistrationRequest;
import com.dsa.core.model.User;

public interface UserServiceInterface {
    void registerNewUser(RegistrationRequest request);
    User authenticateUser(String username, String password);
    User getUserById(Long id);
    Optional<User> findByUsername(String username);
}
