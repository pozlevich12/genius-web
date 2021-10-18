package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.task.model.User;
import com.task.model.enums.EAuthProvider;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select p from User p join fetch p.tasks where p.id = (:id)")
    List<User> findByIdAndFetchTasksEagerly(@Param("id") Long id);

    Optional<User> findByProviderIdAndAuthProvider(String providerId, EAuthProvider authProvider);
}
