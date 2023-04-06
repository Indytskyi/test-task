package com.indytskyi.userservice.services;

import com.indytskyi.userservice.models.User;

public interface AccessService {
    User getUserFromTokenAfterValidation(String bearerToken);
}