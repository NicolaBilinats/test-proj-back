package com.nicola.testproj.dao;

import com.nicola.testproj.model.User;

public interface UserRepository {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    void save(User user);
}
