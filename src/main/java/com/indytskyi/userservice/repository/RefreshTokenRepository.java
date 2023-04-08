package com.indytskyi.userservice.repository;

import com.indytskyi.userservice.model.RefreshToken;
import com.indytskyi.userservice.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    Optional<List<RefreshToken>> findAllByUser(User user);

    Optional<RefreshToken> findByUserEmail(String email);

    Optional<RefreshToken> findByUser(User user);

    void deleteByUser(User user);
}