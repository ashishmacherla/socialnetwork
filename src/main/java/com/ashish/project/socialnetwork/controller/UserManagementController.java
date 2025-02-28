package com.ashish.project.socialnetwork.controller;

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
    public Users addUser(@RequestBody Users users) {
        return userService.addUser(users);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(@PathVariable String userId) {
        userService.removeUser(userId);
    }

    @GetMapping
    public List<Users> listUsers() {
        return userService.listUsers();
    }
}
