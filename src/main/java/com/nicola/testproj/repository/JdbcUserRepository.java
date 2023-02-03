package com.nicola.testproj.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.nicola.testproj.dao.UserRepository;
import com.nicola.testproj.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM user_info WHERE username = ?", new Object[]{username}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM user_info WHERE username = ?", new Object[]{username}, Integer.class) > 0;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO user_info(id, username) VALUES (?, ?)", user.getId(), user.getUsername());
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(UUID.fromString(rs.getString("id")));
            user.setUsername(rs.getString("username"));
            return user;
        }
    }
}


