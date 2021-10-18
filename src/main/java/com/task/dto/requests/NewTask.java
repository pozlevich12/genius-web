package com.task.dto.requests;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.task.model.Answer;
import com.task.model.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewTask {

    @Size(min = 3, max = 64)
    @NotBlank
    private String title;

    @NotBlank
    private String subject;

    @Size(min = 64)
    @NotBlank
    private String body;

    @Size(min = 1, max = 3)
    @NotEmpty
    private Set<Answer> answers;

    private Set<Tag> tags;
}