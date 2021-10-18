package com.task.security.service.oauth2;

import java.util.Map;

import com.task.model.enums.EAuthProvider;

public class GitHubOAuth2UserDetails extends OAuth2UserInfo {

    public GitHubOAuth2UserDetails(Map<String, Object> attributes) {
        super(attributes, EAuthProvider.GITHUB);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getAvatarUrl() {
        return (String) attributes.get("avatar_url");
    }

    @Override
    public EAuthProvider getAuthProvider() {
        return this.authProvider;
    }
}