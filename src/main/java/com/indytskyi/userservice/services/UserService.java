package com.indytskyi.userservice.services;

import com.indytskyi.userservice.models.User;
import com.indytskyi.userservice.models.enums.Color;
import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);

    List<User> getAllUsersWithAgeGreaterThan(Integer age);

    List<String> findNamesOfUsersWithMoreThan3Articles();

    List<User> findUsersByArticlesColor(String color);
}
