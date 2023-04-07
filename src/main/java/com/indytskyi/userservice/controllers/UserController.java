package com.indytskyi.userservice.controllers;

import com.indytskyi.userservice.annotation.IsAdmin;
import com.indytskyi.userservice.dtos.response.UserResponseDto;
import com.indytskyi.userservice.services.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{age}")
    @IsAdmin
    public ResponseEntity<List<UserResponseDto>> getUsersWithAgeGreaterThan(
            @PathVariable Integer age,
            @RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(userService.getAllUsersWithAgeGreaterThan(age));
    }

    @GetMapping("/big-publishers")
    @IsAdmin
    public ResponseEntity<List<String>> findNamesOfUsersWithMoreThan3Articles(
            @RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(userService.findNamesOfUsersWithMoreThan3Articles());
    }

    @GetMapping
    @IsAdmin
    public ResponseEntity<List<UserResponseDto>> findUsersByArticlesColor(
            @RequestParam("color") String color,
            @RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(userService.findUsersByArticlesColor(color));
    }
}
