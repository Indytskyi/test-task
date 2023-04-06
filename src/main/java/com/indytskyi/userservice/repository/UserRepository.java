package com.indytskyi.userservice.repository;

import com.indytskyi.userservice.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByAgeGreaterThan(Integer age);

    @Query("""
           SELECT DISTINCT User
           FROM User
           INNER JOIN Article ON User = Article .user
           WHERE Article .color = :color
                """)
    List<User> findUsersByArticlesColor(String color);

    @Query(
            """
            SELECT DISTINCT User.name
            FROM User JOIN User .articles
            GROUP BY User .id
            HAVING COUNT(Article ) > 3
                    """)
    List<String> findNamesOfUsersWithMoreThan3Articles();
}
