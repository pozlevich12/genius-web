package com.task.payload.response;

import java.util.List;
import lombok.Data;

@Data
public class UserDto {
   
    private Long id;
    private String username;
    private String email;
    private String avatarUrl;
    private List<String> roles;

    public UserDto(Long id, String username, String email, String avatarUrl, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.roles = roles;
    }
}