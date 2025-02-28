package com.ashish.project.socialnetwork.repository;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users1_id", nullable = false)
    private Users users1;

    @ManyToOne
    @JoinColumn(name = "users2_id", nullable = false)
    private Users users2;
}
