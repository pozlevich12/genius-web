package com.task.dto;

import java.util.List;

import com.task.model.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String title;
    private String subject;
    private String body;

}
