package com.security.user.service;

import com.security.user.entity.User;
import com.security.user.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return userRepository.save(user);
    }

    // Save user (used for other purposes)
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not Found"));
    }

    public User updateUser(int id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(()-> new RuntimeException("User not updated"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserDetails loadUserByUsername(String username) {
        return customUserDetailsService.loadUserByUsername(username);
    }
}
