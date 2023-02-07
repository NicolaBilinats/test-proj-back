package com.nicola.testproj.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT id, username FROM user_info",
                (rs, rowNum) -> new User(UUID.fromString(rs.getString("id")), rs.getString("username")));
    }

    public Integer getCountUsersWithAnswers() {
        return jdbcTemplate.queryForObject("SELECT COUNT(DISTINCT user_info.id) " +
                "FROM user_info " +
                "JOIN answer_log " +
                "ON user_info.id = answer_log.user_id;", Integer.class);
    }

    public Integer getCountUsersWithAllAnswers() {
        return jdbcTemplate.queryForObject("SELECT COUNT(DISTINCT ui.id) " +
                "FROM user_info ui " +
                "JOIN answer_log al ON ui.id = al.user_id " +
                "GROUP BY ui.id " +
                "HAVING COUNT(DISTINCT al.question_id) = 5", Integer.class);
    }

    public Integer getCountUsersWithCorrectAnswers() {
        String query = "SELECT COUNT(DISTINCT user_id) FROM ( " +
                "    SELECT user_id, COUNT(user_id) as total_answers FROM ( " +
                "        SELECT user_id, answer_log.question_id, is_correct " +
                "        FROM answer_log" +
                "        LEFT JOIN options_answer ON answer_log.option_id = options_answer.id " +
                "        WHERE option_id IS NOT NULL AND is_correct = true " +
                "        UNION " +
                "        SELECT user_id, answer_log.question_id, is_correct  " +
                "        FROM answer_log " +
                "        LEFT JOIN options_answer ON answer_log.option_id = options_answer.id " +
                "        WHERE option_id IS NULL " +
                "    ) t " +
                "    GROUP BY user_id " +
                "    HAVING total_answers = 5 " +
                ") t2";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public Map<UUID, Double> getPercentOfCorrectAnswersByUser() {
        String sql = "SELECT user_id, COUNT(CASE WHEN is_correct THEN 1 END)/COUNT(*) * 100 as percentage " +
                "FROM (SELECT user_id, question_id, " +
                "CASE " +
                "WHEN questions.type = 'CHOICE' THEN options_answer.is_correct " +
                "WHEN questions.type = 'FREE_TEXT' THEN free_answer.answer = answer_log.free_answer " +
                "END as is_correct " +
                "FROM answer_log " +
                "LEFT JOIN questions ON answer_log.question_id = questions.id " +
                "LEFT JOIN options_answer ON answer_log.option_id = options_answer.id " +
                "LEFT JOIN free_answer ON answer_log.question_id = free_answer.question_id) as results " +
                "GROUP BY user_id";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UUID userId = rs.getObject("user_id", UUID.class);
            Double percent = rs.getDouble("percentage");
            return new AbstractMap.SimpleEntry<>(userId, percent);
        }).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Double getPercentOfCorrectAnswersByUser(String id) {
        String sql = "SELECT (SELECT COUNT(*) FROM answer_log " +
                "LEFT JOIN options_answer ON answer_log.option_id = options_answer.id " +
                "LEFT JOIN questions ON answer_log.question_id = questions.id " +
                "WHERE user_id = ? AND questions.type = 'CHOICE' AND options_answer.is_correct = true) " +
                "+ (SELECT COUNT(*) FROM answer_log " +
                "LEFT JOIN free_answer ON answer_log.free_answer = free_answer.answer " +
                "LEFT JOIN questions ON answer_log.question_id = questions.id " +
                "WHERE user_id = ? AND questions.type = 'FREE_TEXT') " +
                "AS correct_answers_count, " +
                "(SELECT COUNT(*) FROM answer_log WHERE user_id = ?) " +
                "AS total_answers_count";
        Object[] args = new Object[]{id, id, id};
        return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
            int correctAnswersCount = rs.getInt("correct_answers_count");
            int totalAnswersCount = rs.getInt("total_answers_count");
            return (double) correctAnswersCount / totalAnswersCount * 100;
        });
    }

    public boolean isAdmin(String userId) {
        String sql = "SELECT COUNT(*) FROM user_info WHERE id = ? and username = 'admin'";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        return count > 0;
    }
}


