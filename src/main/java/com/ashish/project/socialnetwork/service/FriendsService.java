package com.ashish.project.socialnetwork.service;

import com.ashish.project.socialnetwork.dto.UserDTO;
import com.ashish.project.socialnetwork.exception.model.FriendshipException;
import com.ashish.project.socialnetwork.mapper.Mapper;
import com.ashish.project.socialnetwork.repository.Friendship;
import com.ashish.project.socialnetwork.repository.Users;
import com.ashish.project.socialnetwork.repository.FriendsRepository;
import com.ashish.project.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserRepository userRepository;


    public void createFriendship(Long userId1, Long userId2) throws FriendshipException {
        if (userId1.equals(userId2)) {
            throw new FriendshipException("A user cannot be friends with themselves!", "404");
        }
        Users users1 = userRepository.findById(userId1).orElseThrow(() -> new FriendshipException(String.format("No such userId1:%s exists in DB",userId1), "400"));
        Users users2 = userRepository.findById(userId2).orElseThrow(() -> new FriendshipException(String.format("No such userId2:%s exists in DB",userId2), "400"));

        int exists = friendsRepository.isExistsByUsers1AndUsers2(userId1, userId2);

        if (exists >=1) {
            throw new IllegalStateException("Friendship already exists between " + userId1 + " and " + userId2);
        }

        // Create and save a new friendship
        Friendship friendship = new Friendship();
        friendship.setUsers1(users1);
        friendship.setUsers2(users2);
        friendsRepository.save(friendship);

//        users1.getFriendships().add(friendship);
//        users2.getFriendships().add(friendship);

//        userRepository.save(users1);
//        userRepository.save(users2);
    }

    public void removeFriendship(Long userId1, Long userId2) {
        Users users1 = userRepository.findById(userId1).orElseThrow();
        Users users2 = userRepository.findById(userId2).orElseThrow();

        users1.getFriendships().remove(users2);
        users2.getFriendships().remove(users1);

        userRepository.save(users1);
        userRepository.save(users2);
    }

    public Set<UserDTO> listFriends(Long userId) {
        List<Users> users = friendsRepository.getAllFriendsForUserId(userId);
        Map<Long, Users> friendsUsersMap = new HashMap<>();
        users.parallelStream()
                .flatMap(user -> user.getFriendships().stream())
               // .flatMap(friendship -> friendship.getUsers2())
                .forEach(friendship -> {
                        Users user1 = friendship.getUsers1();
                        Long user1Id = user1.getId();
                        Users user2 = friendship.getUsers2();
                        Long user2Id = user2.getId();
                        if(!friendsUsersMap.containsKey(user1Id) && !user1Id.equals(userId)) {
                            friendsUsersMap.put(user1Id, user1);
                        }

                        if(!friendsUsersMap.containsKey(user2Id) && !user2Id.equals(userId)) {
                            friendsUsersMap.put(user2Id, user2);
                        }

        });
        return friendsUsersMap.values().stream().map(users1 -> {
            return Mapper.toDto(users1);
        }).collect(Collectors.toSet());
    }
}
