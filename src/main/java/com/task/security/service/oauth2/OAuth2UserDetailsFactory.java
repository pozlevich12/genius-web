package com.task.security.service.oauth2;

import java.util.Map;
import com.task.model.EAuthProvider;

public class OAuth2UserDetailsFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(EAuthProvider authProvider, Map<String, Object> attributes) {
        if (authProvider == EAuthProvider.GITHUB) {
            return new GitHubOAuth2UserDetails(attributes);
        }
        throw new RuntimeException("Error, provider not found.");
    }
}