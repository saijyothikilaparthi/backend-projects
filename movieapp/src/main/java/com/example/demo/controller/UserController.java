package com.example.demo.controller;





import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        logger.info("Received signup request: {}", user);

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            logger.error("Email is already taken: {}", user.getEmail());
            return ResponseEntity.badRequest().body("Email is already taken.");
        }
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            logger.error("Username is already taken: {}", user.getUsername());
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        try {
            User registeredUser = userService.registerUser(user);
            logger.info("User registered successfully: {}", registeredUser);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage());
            return ResponseEntity.status(500).body("Server error. Please try again later.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
        logger.info("Received login request: {}", loginRequest);

        Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());
        if (userOptional.isPresent() && userService.checkPassword(userOptional.get(), loginRequest.getPassword())) {
            // Generate JWT token (for simplicity, returning the user details as a response)
            logger.info("Login successful for user: {}", loginRequest.getEmail());
            return ResponseEntity.ok(userOptional.get());
        } else {
            logger.error("Invalid email or password for user: {}", loginRequest.getEmail());
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }
    }
}
