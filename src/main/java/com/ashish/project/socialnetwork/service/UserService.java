package com.ashish.project.socialnetwork.service;

import com.ashish.project.socialnetwork.dto.UserDTO;
import com.ashish.project.socialnetwork.exception.model.UserException;
import com.ashish.project.socialnetwork.mapper.Mapper;
import com.ashish.project.socialnetwork.repository.UserRepository;
import com.ashish.project.socialnetwork.repository.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ashish.project.socialnetwork.Constants.NOT_FOUND_ERROR_CODE;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO addUser(UserDTO userDTO) {
        return Mapper.toDto(userRepository.save(Mapper.toEntity(userDTO)));
    }

    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<UserDTO> listUsers() {
        List<UserDTO> finalList = new ArrayList<>();
        userRepository.findAll().forEach(users -> {
            finalList.add(Mapper.toDto(users));
        });
        return finalList;
    }

    public UserDTO getUserById(Long userId) {
        return Mapper.toDto(userRepository.findById(userId).orElseThrow(()->new UserException(String.format("Passed in User with userId:%s not found in DB",userId), NOT_FOUND_ERROR_CODE)));
    }
}
