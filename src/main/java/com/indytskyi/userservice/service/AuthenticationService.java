package com.indytskyi.userservice.service;


import com.indytskyi.userservice.dto.request.AuthenticationRequestDto;
import com.indytskyi.userservice.dto.request.RefreshTokenRequestDto;
import com.indytskyi.userservice.dto.response.AuthenticationResponse;
import com.indytskyi.userservice.dto.request.RegisterRequestDto;
import com.indytskyi.userservice.dto.response.RegisterResponseDto;
import com.indytskyi.userservice.model.User;


public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequestDto request);

    RegisterResponseDto register(RegisterRequestDto request);

    User validateToken(String bearerToken);

    AuthenticationResponse refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
