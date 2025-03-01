package com.ashish.project.socialnetwork.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    @Query("SELECT u.email FROM Users u WHERE u.email = :email")
    Optional<String> findByEmail(String email);
}
