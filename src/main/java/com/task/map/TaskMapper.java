package com.task.map;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.task.dto.TaskDTO;
import com.task.dto.requests.NewTask;
import com.task.model.Answer;
import com.task.model.Tag;
import com.task.model.Task;
import com.task.model.User;

@Mapper(componentModel = "spring", uses = HelpMapper.class)
public interface TaskMapper {
    
    @Mapping(target = "user", source = "user")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "answers", source = "answers")
    Task from(NewTask newTask, User user, List<Tag> tags, Set<Answer> answers);
    
    TaskDTO from(String title, String subject, String body );
    
}