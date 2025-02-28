package com.ashish.project.socialnetwork.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {

    @Id
    private String id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "users1", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friendship> friendships = new HashSet<>();
}