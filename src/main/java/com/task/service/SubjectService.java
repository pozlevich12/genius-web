package com.task.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.model.Subject;
import com.task.model.enums.ESubject;
import com.task.repository.SubjectRepository;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<String> getAllSubjectsString() {
        return subjectRepository.findAll().stream().map(subject -> subject.getSubject().name())
                .collect(Collectors.toList());
    }

    public Subject getSubjectByName(ESubject subject) {
        return subjectRepository.findBySubject(subject)
                .orElseThrow(() -> new RuntimeException("Error, subject not found."));
    }

}
