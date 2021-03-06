package com.task.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.task.model.Role;
import com.task.model.enums.ERole;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole role);

    Boolean existsByName(ERole role);
}
