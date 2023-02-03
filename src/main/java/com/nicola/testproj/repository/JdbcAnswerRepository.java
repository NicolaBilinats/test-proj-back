package com.nicola.testproj.repository;

import com.nicola.testproj.dao.AnswerRepository;
import com.nicola.testproj.model.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class JdbcAnswerRepository implements AnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAnswerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAnswer(Answer answer) {
        String sql = "INSERT INTO answer_log (user_id, question_id, option_id, free_answer) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, answer.getUserId(), answer.getQuestionId(), answer.getOptionId(), answer.getAnswer());
    }

    @Override
    public List<Answer> findAnswersByUserId(String userId) {
        String sql = "SELECT * FROM answer_log WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new AnswerRowMapper());
    }

    private static class AnswerRowMapper implements RowMapper<Answer> {
        @Override
        public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String userId = resultSet.getString("user_id");
            int questionId = resultSet.getInt("question_id");
            int option_id = resultSet.getInt("option_id");
            String answer = resultSet.getString("free_answer");
            return new Answer(id, UUID.fromString(userId), questionId, option_id, answer);
        }
    }
}
