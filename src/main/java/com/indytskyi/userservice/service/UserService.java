package com.indytskyi.userservice.service;

import com.indytskyi.userservice.dto.response.UserResponseDto;
import com.indytskyi.userservice.model.User;
import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);

    List<UserResponseDto> getAllUsersWithAgeGreaterThan(Integer age);

    List<String> findNamesOfUsersWithMoreThan3Articles();

    List<UserResponseDto> findUsersByArticlesColor(String color);
}
