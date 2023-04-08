package com.indytskyi.userservice.service;

import com.indytskyi.userservice.model.RefreshToken;
import com.indytskyi.userservice.model.User;

public interface RefreshTokenService {
    RefreshToken create(User user);

    RefreshToken findByToken(String token);

    RefreshToken resolveRefreshToken(String token);

    void deleteOldRefreshTokens(User user);
}
