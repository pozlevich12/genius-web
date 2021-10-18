package com.task.security.service.oidc;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.task.model.User;
import com.task.security.service.LoggedUser;

public class OidcUserDetails implements OidcUser, LoggedUser {

    private OidcIdToken idToken;
    private OidcUserInfo userInfo;
    private User user;

    public OidcUserDetails(OidcUser oidcUser, User user) {
        this.idToken = oidcUser.getIdToken();
        this.userInfo = oidcUser.getUserInfo();
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.userInfo.getClaims();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return this.user.getId().toString();
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.userInfo.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return this.userInfo;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public OidcIdToken getIdToken() {
        return this.idToken;
    }

}