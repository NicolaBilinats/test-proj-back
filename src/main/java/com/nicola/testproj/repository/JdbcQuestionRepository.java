package com.nicola.testproj.repository;

import com.nicola.testproj.dao.QuestionRepository;
import com.nicola.testproj.model.Option;
import com.nicola.testproj.model.Question;
import com.nicola.testproj.model.QuestionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcQuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> findAll() {
        String sql = "SELECT id, text, type  FROM questions;";
        return jdbcTemplate.query(sql, new QuestionRowMapper());
    }

    public Question getQuestionWithOptions(int questionId) {
        String sql = "SELECT * FROM questions q LEFT JOIN options_answer oa ON q.id = oa.question_id WHERE q.id = ?";
        return jdbcTemplate.query(sql, new Object[] {questionId}, rs -> {
            Question question = null;
            while (rs.next()) {
                if (question == null) {
                    question = new Question();
                    question.setId(rs.getInt("id"));
                    question.setText(rs.getString("text"));
                    question.setType(QuestionType.valueOf(rs.getString("type")));
                    question.setOptions(new ArrayList<Option>());
                }
                Option option = new Option();
                option.setId(rs.getInt("id"));
                option.setQuestionId(rs.getInt("question_id"));
                option.setText(rs.getString("text"));
                option.setCorrect(rs.getBoolean("is_correct"));
                question.getOptions().add(option);
            }
            return question;
        });
    }


    private static class QuestionRowMapper implements RowMapper<Question> {
        @Override
        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
            Question question = new Question();
            question.setId(rs.getInt("id"));
            question.setText(rs.getString("text"));
            question.setType(QuestionType.valueOf(rs.getString("type")));
            return question;
        }
    }
}

