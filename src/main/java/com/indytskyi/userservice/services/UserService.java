package com.indytskyi.userservice.services;

import com.indytskyi.userservice.models.User;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);
}
