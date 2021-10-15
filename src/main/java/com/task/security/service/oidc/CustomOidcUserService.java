package com.task.security.service.oidc;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.task.model.EAuthProvider;
import com.task.model.User;
import com.task.repository.UserRepository;
import com.task.service.RoleService;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Value("${URL_DEFAULT_VALUE}")
    private static String URL_DEFAULT_VALUE;
    
    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        EAuthProvider authProvider = EAuthProvider
                .valueOf(userRequest.getClientRegistration().getClientName().toUpperCase());
        User user = userRepository.findByProviderIdAndAuthProvider(oidcUser.getSubject(), authProvider)
                .orElseGet(() -> {
                    return getNewUser(oidcUser, authProvider);
                });
        userRepository.save(getUpdateUser(user, oidcUser));
        return new OidcUserDetails(oidcUser, user);
    }

    public User getNewUser(OidcUser oidcUser, EAuthProvider authProvider) {
        User newUser = new User();
        newUser.setProviderId(oidcUser.getSubject());
        newUser.setAuthProvider(authProvider);
        newUser.setUsername(oidcUser.getFullName());
        newUser.setEmail(oidcUser.getEmail());
        newUser.setAvatarUrl(oidcUser.getPicture());
        newUser.getRoles().add(roleService.getDefaultRole());
        return newUser;
    }

    public User getUpdateUser(User user, OidcUser oidcUser) {
        user.setUsername(oidcUser.getFullName());
        user.setEmail(oidcUser.getEmail());
        if (oidcUser.getPicture() == null) {
            user.setAvatarUrl(URL_DEFAULT_VALUE);
        } else {
            user.setAvatarUrl(oidcUser.getPicture());
        }
        user.setLastVisit(LocalDateTime.now());
        return user;
    }
}