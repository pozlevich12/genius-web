package com.task.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Subject;
import com.task.model.Task;
import com.task.model.User;
import com.task.model.enums.ESubject;
import com.task.repository.SubjectRepository;
import com.task.repository.TaskRepository;
import com.task.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubjectRepository subujectRepository;

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        System.out.println("login!");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "asdqwfd");
        return new ResponseEntity<>("YO", headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/user")
    public String user() {
 
        /*
         * Task task = new Task(); task.setBody("my-body"); task.setTitle("my-title");
         * 
         * Subject s = subujectRepository.findById((long) 1).orElseThrow();
         * task.setSubject(s); User ust = getusr((long) 15); task.setUser(ust);
         * Optional<User> use2r = userRepository.findByIdAndFetchTasksEagerly((long)
         * 15); task.setUser(use2r); taskRepository.save(task); List<User> ugsd =
         * userRepository.findByIdAndFetchTasksEagerly((long) 15);
         * 
         * userRepository.delete(ugsd);
         */

        return "User content.";
    }

    /*
     * @Transactional public User getusr(Long id) { User user =
     * userRepository.findById(id).orElseThrow(); user.getTasks().size(); return
     * user; }
     */

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}