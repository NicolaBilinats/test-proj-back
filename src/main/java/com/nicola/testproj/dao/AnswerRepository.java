package com.nicola.testproj.dao;

import com.nicola.testproj.model.Answer;
import com.nicola.testproj.model.Question;

import java.util.List;

public interface AnswerRepository {
    void saveAnswer(String userId, Answer answer);
    List<Answer> findAnswersByUserId(String userId);
}
