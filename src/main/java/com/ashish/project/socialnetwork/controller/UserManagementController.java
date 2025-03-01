package com.ashish.project.socialnetwork.controller;

import com.ashish.project.socialnetwork.dto.UserDTO;
import com.ashish.project.socialnetwork.repository.Users;
import com.ashish.project.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userManagement")
@DependsOn("userService")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(@PathVariable Long userId) {
        userService.removeUser(userId);
    }

    @GetMapping("/id/{userId}")
    public UserDTO getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/listAllUsers")
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }
}
