package com.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.task.model.Subject;
import com.task.model.enums.ESubject;

@Repository
@Transactional
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubject(ESubject subject);
    
}
