package com.task.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.model.User;
import com.task.payload.response.UserDto;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/profile")
    public ResponseEntity<?> losgin(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList());
        return ResponseEntity
                .ok(new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getAvatarUrl(), roles));
    }
}