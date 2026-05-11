package com.ra5.projecte3.mapper;

import org.springframework.stereotype.Component;

import com.ra5.projecte3.dto.UserRequestDTO;
import com.ra5.projecte3.model.User;

@Component
public class UserMapper {
    
    public UserRequestDTO toDto(User user) {

        if (user == null) {
            return null;
        
        return new UserRequestDTO(
            user.ge
        );
    }
}
