package com.task.security.service.oauth2;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.task.model.User;
import com.task.security.service.LoggedUser;

public class OAuth2UserDetails implements OAuth2User, LoggedUser {

    private OAuth2UserInfo oAuth2UserInfo;
    private User user;

    public OAuth2UserDetails(OAuth2UserInfo oAuth2UserInfo, User user) {
        this.oAuth2UserInfo = oAuth2UserInfo;
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.oAuth2UserInfo.getAttributes();
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

    public String getProviderId() {
        return this.oAuth2UserInfo.getProviderId();
    }

    @Override
    public User getUser() {
        return this.user;
    }
}