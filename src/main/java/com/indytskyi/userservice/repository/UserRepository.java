package com.indytskyi.userservice.repository;

import com.indytskyi.userservice.model.User;
import com.indytskyi.userservice.model.enums.Color;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findUserByAgeGreaterThan(Integer age);

    @Query("""
           SELECT DISTINCT u
           FROM User u
           LEFT JOIN FETCH u.articles a
           Where a.color = :color
                """)
    List<User> findUsersByArticlesColor(Color color);

    @Query("""
            SELECT u.name
            FROM User u
            LEFT JOIN Article a ON u = a.user
            GROUP BY u.name
            HAVING COUNT(a.user) > 3
            """)
    List<String> findNamesOfUsersWithMoreThan3Articles();
}
