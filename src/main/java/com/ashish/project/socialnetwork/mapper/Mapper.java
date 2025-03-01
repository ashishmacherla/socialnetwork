package com.ashish.project.socialnetwork.mapper;

import com.ashish.project.socialnetwork.dto.UserDTO;
import com.ashish.project.socialnetwork.repository.Users;

public class Mapper {
    public static UserDTO toDto(Users users) {
        return new UserDTO(users.getId(), users.getName(), users.getEmail());
    }

    public static Users toEntity(UserDTO userDTO) {
        return new Users(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), null);
    }
}
