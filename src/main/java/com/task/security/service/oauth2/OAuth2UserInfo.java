package com.task.security.service.oauth2;

import java.util.Map;

import com.task.model.enums.EAuthProvider;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;
    protected EAuthProvider authProvider;
    
    public OAuth2UserInfo(Map<String, Object> attributes, EAuthProvider authProvider) {
        this.attributes = attributes;
        this.authProvider = authProvider;
    }
 
    public Map<String, Object> getAttributes() {
        return attributes;
    }
 
    public abstract String getProviderId();
 
    public abstract String getName();
 
    public abstract String getEmail();
 
    public abstract String getAvatarUrl();
    
    public abstract EAuthProvider getAuthProvider();
}
