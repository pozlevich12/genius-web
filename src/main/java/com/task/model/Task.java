package com.task.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "edit")
    private boolean edit = false;

    @Column(name = "update_date", nullable = false)
    private LocalDateTime update = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tags_by_tasks", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "task")
    private Set<CurrentRatingOfTask> voteList = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Img> imgUrls = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "task")
    private Set<UserAnswer> usersAnswers = new HashSet<>();

}
