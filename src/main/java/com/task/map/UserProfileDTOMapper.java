package com.task.map;

import org.mapstruct.Mapper;
import com.task.dto.UserProfileDTO;
import com.task.model.User;

@Mapper(componentModel = "spring", uses = HelpMapper.class)
public interface UserProfileDTOMapper {

    
    UserProfileDTO from(User user);
}