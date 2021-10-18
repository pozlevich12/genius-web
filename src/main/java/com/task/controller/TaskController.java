package com.task.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.AddTaskParam;
import com.task.dto.TaskDTO;
import com.task.dto.requests.NewTask;
import com.task.model.Task;
import com.task.model.User;
import com.task.security.service.LoggedUser;
import com.task.service.SubjectService;
import com.task.service.TagService;
import com.task.service.TaskService;
import com.task.service.UserService;

@RestController

public class TaskController {

    @Autowired
    private TagService tagService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;
    
    /*
     * @Autowired private TaskMapper taskMapper;
     */
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/add")
    public AddTaskParam getAddTaskParam(Authentication authentication) {
        return new AddTaskParam(subjectService.getAllSubjectsString(), tagService.getAllTagsString());
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/addtask")
    public ResponseEntity<?> addNewTask(@Valid @RequestBody NewTask newTask, Authentication authentication) {
        Long userId = ((LoggedUser) authentication.getPrincipal()).getUser().getId();
        
        Task task = taskService.saveNewTask(newTask, userId);
        return ResponseEntity.ok(new Task());
    }
    
    @GetMapping("/")
    public List<TaskDTO> getAllTasks() {
        List<TaskDTO> taskDTO = new ArrayList<TaskDTO>();
        taskDTO = taskService.getAllTasks();
        return taskDTO;
        
    }

    

}
