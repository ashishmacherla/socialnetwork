package com.ashish.project.socialnetwork.service;

import com.ashish.project.socialnetwork.repository.Users;
import com.ashish.project.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users addUser(Users users) {
        return userRepository.save(users);
    }

    public void removeUser(String userId) {
        userRepository.deleteById(userId);
    }

    public List<Users> listUsers() {
        return null;
    }

    public Optional<Users> getUserById(String userId) {
        return userRepository.findById(userId);
    }
}
