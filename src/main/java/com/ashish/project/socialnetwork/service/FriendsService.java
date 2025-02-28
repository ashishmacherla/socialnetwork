package com.ashish.project.socialnetwork.service;

import com.ashish.project.socialnetwork.repository.Users;
import com.ashish.project.socialnetwork.repository.FriendsRepository;
import com.ashish.project.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserRepository userRepository;


    public void createFriendship(String userId1, String userId2) {
        Users users1 = userRepository.findById(userId1).orElseThrow();
        Users users2 = userRepository.findById(userId2).orElseThrow();

//        users1.getFriends().add(users2);
//        users2.getFriends().add(users1);

        userRepository.save(users1);
        userRepository.save(users2);
    }

    public void removeFriendship(String userId1, String userId2) {
        Users users1 = userRepository.findById(userId1).orElseThrow();
        Users users2 = userRepository.findById(userId2).orElseThrow();

//        users1.getFriends().remove(users2);
//        users2.getFriends().remove(users1);

        userRepository.save(users1);
        userRepository.save(users2);
    }

    public Set<Users> listFriends(String userId) {
        Users users = userRepository.findById(userId).orElseThrow();
//        return users.getFriends();
        return null;
    }
}
