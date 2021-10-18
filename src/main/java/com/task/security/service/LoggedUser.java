package com.task.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.task.model.User;

public interface LoggedUser {
    public User getUser();

    public Collection<? extends GrantedAuthority> getAuthorities();
}