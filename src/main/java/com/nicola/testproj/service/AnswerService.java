package com.nicola.testproj.service;

import java.util.List;

import com.nicola.testproj.model.Answer;
import com.nicola.testproj.repository.JdbcAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private JdbcAnswerRepository jdbcAnswerRepository;

    public void addAnswer(String userId, Answer answer) {
        jdbcAnswerRepository.saveAnswer(userId, answer);
    }

    public List<Answer> getAnswersByUserId(String userId) {
        return jdbcAnswerRepository.findAnswersByUserId(userId);
    }
}

