package com.indytskyi.userservice.services.impl;

import com.indytskyi.userservice.dtos.request.AuthenticationRequestDto;
import com.indytskyi.userservice.dtos.response.AuthenticationResponse;
import com.indytskyi.userservice.dtos.request.RegisterRequestDto;
import com.indytskyi.userservice.dtos.response.RegisterResponseDto;
import com.indytskyi.userservice.models.enums.Role;
import com.indytskyi.userservice.models.User;
import com.indytskyi.userservice.security.jwt.JwtService;
import com.indytskyi.userservice.services.AuthenticationService;
import com.indytskyi.userservice.services.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j

public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
        log.info("Straring Authenticate user with email = {}", request.email());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userService.findUserByEmail(request.email());
        var jwtToken = jwtService.generateToken(Map.of("ROLE", user.getRole()), user);

        return new AuthenticationResponse(jwtToken);
    }

    @Override
    @Transactional
    public RegisterResponseDto register(RegisterRequestDto request) {
        log.info("Registering user with email = {} and name = {}",
                request.getEmail(), request.getName());

        userService.checkIfUserWithNewEmailExist(request.getEmail());
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .age(request.getAge())
                .role(Role.USER)
                .build();

        userService.saveUser(user);
        var jwtToken = jwtService.generateToken(Map.of("ROLE", user.getRole()), user);
        return new RegisterResponseDto(jwtToken);
    }

    @Override
    public User validateToken(String bearerToken) {
        log.info("Check if token is valid");

        var token = jwtService.resolveToken(bearerToken);
        jwtService.validateToken(token);
        var email = jwtService.getUserName(token);

        return userService.findUserByEmail(email);
    }
}
