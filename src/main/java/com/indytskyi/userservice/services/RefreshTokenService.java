package com.indytskyi.userservice.services;

import com.indytskyi.userservice.models.RefreshToken;
import com.indytskyi.userservice.models.User;

public interface RefreshTokenService {
    RefreshToken create(User user);

    RefreshToken findByToken(String token);

    RefreshToken resolveRefreshToken(String token);

    void deleteOldRefreshTokens(User user);
}
