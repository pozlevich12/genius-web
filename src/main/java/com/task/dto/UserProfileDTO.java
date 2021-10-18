package com.task.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime firstVisit;
    private LocalDateTime lastVisit;
    private String avatarUrl;
    private String language;
    private String style;
    private List<String> roles;
}