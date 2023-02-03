package com.nicola.testproj.dao;

import com.nicola.testproj.model.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> findAll();
    Question getQuestionWithOptions(int questionId);
}
