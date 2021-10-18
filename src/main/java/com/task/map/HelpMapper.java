package com.task.map;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.task.model.Role;
import com.task.model.Subject;
import com.task.model.enums.ESubject;
import com.task.service.SubjectService;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class HelpMapper {

    @Autowired
    private SubjectService subjectService;

    public List<String> mapRoleDTO(Set<Role> roles) {
        return roles.stream().map(role -> role.getName().name()).collect(Collectors.toList());
    }

    public Subject mapSubjectEntity(String subject) {
        return subjectService.getSubjectByName(ESubject.valueOf(subject));
    }
}