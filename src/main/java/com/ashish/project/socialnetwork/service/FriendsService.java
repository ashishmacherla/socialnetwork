package com.ashish.project.socialnetwork.service;

import com.ashish.project.socialnetwork.dto.UserDTO;
import com.ashish.project.socialnetwork.exception.model.FriendshipException;
import com.ashish.project.socialnetwork.mapper.Mapper;
import com.ashish.project.socialnetwork.repository.FriendsRepository;
import com.ashish.project.socialnetwork.repository.Friendship;
import com.ashish.project.socialnetwork.repository.UserRepository;
import com.ashish.project.socialnetwork.repository.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new FriendshipException(String.format("Friendship already exists between %s and %s", userId1, userId2),"400");
        }

        Friendship friendship = new Friendship();
        friendship.setUsers1(users1);
        friendship.setUsers2(users2);
        friendsRepository.save(friendship);
    }

    public void removeFriendship(Long userId1, Long userId2) {
        List<Long> friendshipIds = friendsRepository.getAllFriendsForUserId1AndUserId2(userId1, userId2);

        if(friendshipIds ==null || friendshipIds.size() == 0) {
            throw new FriendshipException(String.format("No Friendship exists between userId1:%s and userId2:%s", userId1,userId2), "400");
        }
        friendsRepository.deleteAllById(friendshipIds);
    }

    public List<UserDTO> listFriends(Long userId) {
        List<Users> users = friendsRepository.getAllFriendsForUserId(userId);
        if(users != null && users.size() ==0) throw new FriendshipException(String.format("No friends found for passed-in userId:%s", userId), "404");

        return users.parallelStream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }
}
