package com.indytskyi.userservice.controllers;

import com.indytskyi.userservice.models.User;
import com.indytskyi.userservice.services.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{age}")
    public ResponseEntity<List<User>> getUsersWithAgeGreaterThan(@PathVariable Integer age) {
        return ResponseEntity.ok(userService.getAllUsersWithAgeGreaterThan(age));
    }

    @GetMapping("/big-publishers")
    public ResponseEntity<List<String>> findNamesOfUsersWithMoreThan3Articles() {
        return ResponseEntity.ok(userService.findNamesOfUsersWithMoreThan3Articles());
    }

    @GetMapping
    public ResponseEntity<List<User>> findNamesOfUsersWithMoreThan3Articles(@RequestParam("color") String color) {
        return ResponseEntity.ok(userService.findUsersByArticlesColor(color));
    }
}
