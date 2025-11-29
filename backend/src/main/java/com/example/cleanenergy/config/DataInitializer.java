package com.example.cleanenergy.config;

import com.example.cleanenergy.entity.User;
import com.example.cleanenergy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create a default user for development if none exists
        if (userRepository.count() == 0) {
            String username = "thanmay";
            String rawPassword = "thanmay";
            String encoded = passwordEncoder.encode(rawPassword);
            User u = new User(username, encoded);
            userRepository.save(u);
            System.out.println("Created default user 'thanmay' with password 'thanmay' (BCrypt encoded) for development.");
        }
    }
}
