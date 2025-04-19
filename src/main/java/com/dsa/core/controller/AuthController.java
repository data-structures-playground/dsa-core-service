package com.dsa.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.core.dto.RegistrationRequest;
import com.dsa.core.model.User;
import com.dsa.core.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            userService.registerNewUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

	/*
	 * @PostMapping("/login") public ResponseEntity<String> login(@RequestBody
	 * RegistrationRequest loginRequest, HttpSession session) { User user =
	 * userService.authenticateUser(loginRequest.getUsername(),
	 * loginRequest.getPassword()); if (user != null) {
	 * session.setAttribute("userId", user.getId()); return
	 * ResponseEntity.ok("Login successful"); } else { return
	 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); }
	 * }
	 */

	/*
	 * @PostMapping("/logout") public ResponseEntity<String> logout(HttpSession
	 * session) { session.invalidate(); return
	 * ResponseEntity.ok("Logged out successfully"); }
	 */
}
