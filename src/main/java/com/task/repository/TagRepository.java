package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.model.Tag;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag, Long>{

}
