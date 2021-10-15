package com.task.security.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;
import com.task.security.jwt.JwtUtils;

public class MySimpleAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${URL_REDIRECT_HOME}")
    private static String URL_REDIRECT_HOME;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String jwt = jwtUtils.generateJwtToken(authentication);
        String determineTargetUrl = UriComponentsBuilder.fromUriString(URL_REDIRECT_HOME).queryParam("JWT", jwt).build()
                .toUriString();
        getRedirectStrategy().sendRedirect(request, response, determineTargetUrl);
    }
}