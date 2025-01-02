package com.example.globaldorm.service;

import com.example.globaldorm.model.User;
import com.example.globaldorm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
        String input = password + salt;
        byte[] hash = digest.digest(input.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    public String registerUser(String username, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return "Error: User already exists!";
        }

        try {
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);

            User newUser = new User(UUID.randomUUID().toString(), username, hashedPassword, salt);
            userRepository.save(newUser);

            return "User registered successfully!";
        } catch (Exception e) {
            return "Error: Unable to register user!";
        }
    }

    public String loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return "Error: Invalid username or password!";
        }

        User user = userOptional.get();
        try {
            String hashedInputPassword = hashPassword(password, user.getSalt());
            if (hashedInputPassword.equals(user.getPassword())) {
                return "Login successful!";
            } else {
                return "Error: Invalid username or password!";
            }
        } catch (Exception e) {
            return "Error: Unable to authenticate user!";
        }
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
