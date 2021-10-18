package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.dto.UserProfileDTO;
import com.task.map.UserProfileDTOMapper;
import com.task.model.User;
import com.task.security.service.LoggedUser;
import com.task.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileDTOMapper userProfileDTOMapper;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/profile")
    public UserProfileDTO getProfile(Authentication authentication) {
        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();
        User user = userService.getUser(loggedUser.getUser().getId());
        UserProfileDTO asd = userProfileDTOMapper.from(user);
        System.out.println(asd.getLastVisit());
        return userProfileDTOMapper.from(user);
    }
}