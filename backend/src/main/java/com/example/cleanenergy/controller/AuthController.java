package com.example.cleanenergy.controller;

import com.example.cleanenergy.config.JwtUtil;
import com.example.cleanenergy.entity.User;
import com.example.cleanenergy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Missing username or password");
        }

        // load user from DB
        return userRepository.findByUsername(username)
                .map(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        String token = jwtUtil.generateToken(username);
                        return ResponseEntity.ok(Map.of("token", token));
                    } else {
                        return ResponseEntity.status(401).body("Invalid credentials");
                    }
                })
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        if (password.length() < 6) {
            return ResponseEntity.badRequest().body("Password must be at least 6 characters long");
        }

        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.status(409).body("Username already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

}