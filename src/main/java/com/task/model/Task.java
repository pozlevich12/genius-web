package com.task.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    @OneToMany
    @JoinColumn(name = "task_id")
    private Set<Answer> answers;
    
    
    /*
     * @ManyToOne --> id_user
     * */
    
    @Column(name = "subject", nullable = false)
    private ESubject subject;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "body", nullable = false)
    private String body;
    
    @Column(name = "edit")
    private boolean edit;
    
    @Column(name = "createDate")
    private LocalDate createDate;
    
    
    
}
