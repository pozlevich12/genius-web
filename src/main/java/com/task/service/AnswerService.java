package com.task.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.model.Answer;
import com.task.repository.AnswerRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    public Set<Answer> saveAnswers(Set<Answer> answers) {
        answerRepository.saveAll(answers);
        return answers;
    }

}