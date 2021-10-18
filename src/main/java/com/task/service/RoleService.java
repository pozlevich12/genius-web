package com.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.model.Role;
import com.task.model.enums.ERole;
import com.task.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void addUserRole(ERole name) {
        if (!roleRepository.existsByName(name)) {
            roleRepository.save(new Role(name));
        }
    }

    public Role getDefaultRole() {
        return roleRepository.findByName(ERole.ROLE_USER).orElseGet(() -> {
            Role defaultRole = new Role(ERole.ROLE_USER);
            roleRepository.save(defaultRole);
            return defaultRole;
        });
    }
}