package com.indytskyi.userservice.controllers;


import com.indytskyi.userservice.dtos.request.AuthenticationRequestDto;
import com.indytskyi.userservice.dtos.request.RefreshTokenRequestDto;
import com.indytskyi.userservice.dtos.request.RegisterRequestDto;
import com.indytskyi.userservice.dtos.response.AuthenticationResponse;
import com.indytskyi.userservice.dtos.response.RegisterResponseDto;
import com.indytskyi.userservice.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto request) {
        return new ResponseEntity<>(authenticationService.register(request),
                HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            @RequestBody RefreshTokenRequestDto refreshTokenRequestDto
    ) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequestDto));
    }

}
