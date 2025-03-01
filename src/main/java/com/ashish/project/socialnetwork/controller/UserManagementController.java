package com.ashish.project.socialnetwork.controller;

import com.ashish.project.socialnetwork.dto.UserDTO;
import com.ashish.project.socialnetwork.exception.model.UserException;
import com.ashish.project.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userManagement")
@DependsOn("userService")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public UserDTO addUser(@RequestBody UserDTO userDTO) throws UserException {
        return userService.addUser(userDTO);
    }

    @DeleteMapping("remove/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable Long userId) {
        userService.removeUser(userId);
        return ResponseEntity.ok(String.format("UserId:%s deleted successfully", userId));
    }

    @GetMapping("getBy/id/{userId}")
    public UserDTO getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/listAllUsers")
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }
}
