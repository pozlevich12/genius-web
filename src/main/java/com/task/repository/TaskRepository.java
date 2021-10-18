package com.task.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.model.Task;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

   
}
