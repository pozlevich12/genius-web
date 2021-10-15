package com.task.security.service.oauth2;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.task.model.EAuthProvider;
import com.task.model.User;
import com.task.repository.UserRepository;
import com.task.service.RoleService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public OAuth2UserDetails loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        EAuthProvider authProvider = EAuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getClientName().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserDetailsFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
        
        User user = userRepository.findByProviderIdAndAuthProvider(oAuth2UserInfo.getProviderId(), authProvider)
                .orElseGet(() -> {
                    return getNewUser(oAuth2User, authProvider);
                });
        userRepository.save(getUpdateUser(oAuth2UserInfo, user));
        return new OAuth2UserDetails(oAuth2UserInfo, user);
    }

    public User getNewUser(OAuth2User oAuth2User, EAuthProvider authProvider) {
        User newUser = new User();
        OAuth2UserInfo custOAuth2User = OAuth2UserDetailsFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
        newUser.setProviderId(custOAuth2User.getProviderId());
        newUser.setAuthProvider(custOAuth2User.getAuthProvider());
        newUser.setUsername(custOAuth2User.getName());
        newUser.setEmail(custOAuth2User.getEmail());
        newUser.setAvatarUrl(custOAuth2User.getAvatarUrl());
        newUser.getRoles().add(roleService.getDefaultRole());
        return newUser;
    }

    public User getUpdateUser(OAuth2UserInfo oAuth2UserInfo, User user) {
        user.setUsername(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setAvatarUrl(oAuth2UserInfo.getAvatarUrl());
        user.setLastVisit(LocalDateTime.now());
        return user;
    }
}