package com.task.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.dto.TaskDTO;
import com.task.dto.requests.NewTask;
import com.task.map.TaskMapper;
import com.task.model.Answer;
import com.task.model.Tag;
import com.task.model.Task;
import com.task.model.User;
import com.task.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    public Task saveNewTask(NewTask newTask, Long userId) {
        User user = userService.getUser(userId);
        List<Tag> tags = tagService.saveTags(newTask.getTags());
        // Set<Answer> answers = answerService.saveAnswers(newTask.getAnswers());
        List<Answer> addAnswers = newTask.getAnswers().stream().map(answer -> answer).collect(Collectors.toList());
        Task task = taskMapper.from(newTask, user, tags, addAnswers);
        taskRepository.save(task);
        return task;
    }

    @Transactional
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            tasksDTO.add(taskMapper.from(tasks.get(i).getTitle(), tasks.get(i).getSubject().toString(), tasks.get(i).getBody()));
        }
        return tasksDTO;
    }

}