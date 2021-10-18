package com.task.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.task.model.User;
import com.task.security.service.LoggedUser;

public class UserDetailsImpl implements LoggedUser {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

}
