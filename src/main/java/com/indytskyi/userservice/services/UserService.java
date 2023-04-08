package com.indytskyi.userservice.services;

import com.indytskyi.userservice.dtos.response.UserResponseDto;
import com.indytskyi.userservice.models.User;
import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);

    List<UserResponseDto> getAllUsersWithAgeGreaterThan(Integer age);

    List<String> findNamesOfUsersWithMoreThan3Articles();

    List<UserResponseDto> findUsersByArticlesColor(String color);
}
