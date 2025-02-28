package com.ashish.project.socialnetwork.controller;

import com.ashish.project.socialnetwork.repository.Users;
import com.ashish.project.socialnetwork.service.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/friendshipManagement/friend")
@RequiredArgsConstructor
public class FriendsManagementController {

    @Autowired
    private FriendsService friendsService;

    @PostMapping("/{userId1}/{userId2}")
    public void createFriendship(@PathVariable String userId1, @PathVariable String userId2) {
        friendsService.createFriendship(userId1, userId2);
    }

    @DeleteMapping("/{userId1}/{userId2}")
    public void removeFriendship(@PathVariable String userId1, @PathVariable String userId2) {
        friendsService.removeFriendship(userId1, userId2);
    }

    @GetMapping("/{userId}")
    public Set<Users> listFriends(@PathVariable String userId) {
        return friendsService.listFriends(userId);
    }
}
