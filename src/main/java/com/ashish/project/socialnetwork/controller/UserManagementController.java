package com.ashish.project.socialnetwork.controller;

import com.ashish.project.socialnetwork.dto.UserDTO;
import com.ashish.project.socialnetwork.exception.model.UserException;
import com.ashish.project.socialnetwork.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

import static com.ashish.project.socialnetwork.Constants.EMAIL_PATTERN;

@RestController
@RequestMapping("/userManagement/user")
@DependsOn("userService")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser( @Valid @RequestBody UserDTO userDTO) throws UserException {
        customRequestValidator(userDTO);
        return ResponseEntity.accepted().body(userService.addUser(userDTO));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) throws UserException {
        customRequestValidator(userDTO);
        return ResponseEntity.ok().body(userService.updateUser(userDTO));
    }


    @DeleteMapping("remove/id/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable Long userId) {
        userService.removeUser(userId);
        return ResponseEntity.ok(String.format("UserId:%s deleted successfully", userId));
    }

    @GetMapping("getBy/id/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/listAllUsers")
    public ResponseEntity<List<UserDTO>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    private void customRequestValidator(UserDTO userDTO) {
        String userName = userDTO.getName();
        String userEmail = userDTO.getEmail();
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if(userName == null || userName.isEmpty() || userName.isBlank()) {
            throw new UserException("Name is a mandatory attribute and cannot null/blank/empty","400");
        }

        if(userEmail == null || userEmail.isEmpty() || userEmail.isBlank()) {
            throw new UserException("Email is a mandatory attribute and cannot null/blank/empty","400");
        } else if(!pattern.matcher(userEmail).matches()) {
            throw new UserException("Incorrect email format","400");
        }
    }
}
