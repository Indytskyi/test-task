package com.indytskyi.userservice.services;


import com.indytskyi.userservice.dtos.request.AuthenticationRequestDto;
import com.indytskyi.userservice.dtos.request.RefreshTokenRequestDto;
import com.indytskyi.userservice.dtos.response.AuthenticationResponse;
import com.indytskyi.userservice.dtos.request.RegisterRequestDto;
import com.indytskyi.userservice.dtos.response.RegisterResponseDto;
import com.indytskyi.userservice.models.User;


public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequestDto request);

    RegisterResponseDto register(RegisterRequestDto request);

    User validateToken(String bearerToken);

    AuthenticationResponse refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
