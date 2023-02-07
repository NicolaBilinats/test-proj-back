package com.nicola.testproj.service;

import com.nicola.testproj.model.User;
import com.nicola.testproj.repository.JdbcUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean isAdmin(String userId) {
        return userRepository.isAdmin(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Integer getCountUsersWithAnswers() {
        return userRepository.getCountUsersWithAnswers();
    }

    public Integer getCountUsersWithAllAnswers() {
        return userRepository.getCountUsersWithAllAnswers();
    }

    public Integer getCountUsersWithAllCorrectAnswers() {
        return userRepository.getCountUsersWithCorrectAnswers();
    }

    public Double getPercentOfCorrectAnswersByUser(String userId) {
        return userRepository.getPercentOfCorrectAnswersByUser(userId);
    }
}
