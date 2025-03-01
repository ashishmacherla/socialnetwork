package com.ashish.project.socialnetwork.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("friendsRepository")
public interface FriendsRepository extends CrudRepository<Friendship, Long> {

    @Query("SELECT COUNT(f) FROM Friendship f " +
            "WHERE (f.users1.id = :userId1 AND f.users2.id = :userId2) " +
            "   OR (f.users1.id = :userId2 AND f.users2.id = :userId1)")
    int isExistsByUsers1AndUsers2(Long userId1, Long userId2);


//    @Query("SELECT u FROM Users u " +
//            "JOIN Friendship f ON (f.users1.id = u.id OR f.users2.id = u.id) " +
//            "WHERE u.id = :userId1")
@Query("SELECT u FROM Users u WHERE u.id IN " +
        "(SELECT f.users2.id FROM Friendship f WHERE f.users1.id = :userId" +
        " UNION ALL " +
        "SELECT f.users1.id FROM Friendship f WHERE f.users2.id = :userId)")
    List<Users> getAllFriendsForUserId(@Param("userId") Long userId);
}
