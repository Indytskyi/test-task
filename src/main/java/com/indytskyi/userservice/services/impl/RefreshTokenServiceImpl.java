package com.indytskyi.userservice.services.impl;


import com.indytskyi.userservice.exception.ObjectNotFoundException;
import com.indytskyi.userservice.models.RefreshToken;
import com.indytskyi.userservice.models.User;
import com.indytskyi.userservice.repository.RefreshTokenRepository;
import com.indytskyi.userservice.services.RefreshTokenService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${jwt.refresh.token.expiredTime.min}")
    private long expirationPeriod;


    @Override
    @Transactional
    public RefreshToken create(User user) {
        var refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        String token = UUID.randomUUID().toString();
        while (refreshTokenRepository.findByToken(token).isPresent()) {
            token = UUID.randomUUID().toString();
        }
        refreshToken.setToken(token);
        refreshToken.setExpiredAt(LocalDateTime.now()
                .plusMinutes(expirationPeriod));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Refresh token: " + token + " wasn't found in a DB."
                ));
    }


    @Override
    public RefreshToken resolveRefreshToken(String token) {
        RefreshToken refreshToken = findByToken(token);
        if (refreshToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ObjectNotFoundException("Refresh token was expired. Please, make a new login.");
        }
        return refreshToken;
    }

    @Override
    public void deleteOldRefreshTokens(User user) {
        var refreshToken = refreshTokenRepository.findByUser(user);
        refreshToken.ifPresent(token -> refreshTokenRepository.deleteById(token.getId()));
    }
}
