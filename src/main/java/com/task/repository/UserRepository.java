package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.task.model.User;
import com.task.model.enums.EAuthProvider;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByProviderIdAndAuthProvider(String providerId, EAuthProvider authProvider);
}
