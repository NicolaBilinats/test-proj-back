package com.nicola.testproj.dao;

import com.nicola.testproj.model.Answer;

import java.util.List;

public interface AnswerRepository {
    void saveAnswer(String userId, Answer answer);
    List<Answer> findAnswersByUserId(String userId);
}
