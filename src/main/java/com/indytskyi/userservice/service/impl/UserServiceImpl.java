package com.indytskyi.userservice.service.impl;

import com.indytskyi.userservice.dto.response.UserResponseDto;
import com.indytskyi.userservice.exception.ObjectNotFoundException;
import com.indytskyi.userservice.model.User;
import com.indytskyi.userservice.model.enums.Color;
import com.indytskyi.userservice.repository.UserRepository;
import com.indytskyi.userservice.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        log.info("Find user by email = {}", email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("User with email = " + email + "NOT FOUND"));
    }

    @Override
    public void saveUser(User user) {
        log.info("Save user with email = {}", user.getEmail());

        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> getAllUsersWithAgeGreaterThan(Integer age) {
        log.info("Try to get users with age grater than = {}", age);

        return userRepository.findUserByAgeGreaterThan(age)
                .stream()
                .map(this::mappedUsertoDto)
                .toList();
    }

    @Override
    public List<String> findNamesOfUsersWithMoreThan3Articles() {
        log.info("Try to find names of users with more than 3 articles");

        return userRepository.findNamesOfUsersWithMoreThan3Articles();
    }

    @Override
    public List<UserResponseDto> findUsersByArticlesColor(String color) {
        log.info("Try to find users by articles color = {}", color);

        return userRepository.findUsersByArticlesColor(Color.valueOf(color))
                .stream()
                .map(this::mappedUsertoDto)
                .toList();

    }

    private UserResponseDto mappedUsertoDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .articles(user.getArticles())
                .build();
    }

}
