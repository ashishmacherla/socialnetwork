package com.ashish.project.socialnetwork.controller;

import com.ashish.project.socialnetwork.dto.UserDTO;
import com.ashish.project.socialnetwork.exception.model.FriendshipException;
import com.ashish.project.socialnetwork.service.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendshipManagement")
@RequiredArgsConstructor
public class FriendsManagementController {

    @Autowired
    private FriendsService friendsService;

    @PostMapping("createFriendship/{userId1}/{userId2}")
    public ResponseEntity<String> createFriendship(@PathVariable Long userId1, @PathVariable Long userId2) throws FriendshipException {
        friendsService.createFriendship(userId1, userId2);
        return ResponseEntity.accepted().body(String.format("Successfully created friendship between userId1:%s and userId2:%s", userId1, userId2));
    }

    @DeleteMapping("removeFriendship/{userId1}/{userId2}")
    public ResponseEntity<String> removeFriendship(@PathVariable Long userId1, @PathVariable Long userId2) {
        friendsService.removeFriendship(userId1, userId2);
        return ResponseEntity.ok().body(String.format("Successfully deleted friendship between userId1:%s and userId2:%s", userId1, userId2));
    }

    @GetMapping("listAllFrienshipsOfUser/id/{userId}")
    public ResponseEntity<List<UserDTO>> listFriends(@PathVariable Long userId) {
        return ResponseEntity.ok(friendsService.listFriends(userId));
    }
}
