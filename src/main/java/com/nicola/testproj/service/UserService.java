package com.nicola.testproj.service;

import com.nicola.testproj.model.User;
import com.nicola.testproj.repository.JdbcUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JdbcUserRepository userRepository;

    public UserService(JdbcUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
