package com.indytskyi.userservice.services;


import com.indytskyi.userservice.dtos.AuthenticationRequestDto;
import com.indytskyi.userservice.dtos.AuthenticationResponse;
import com.indytskyi.userservice.dtos.RegisterRequestDto;
import com.indytskyi.userservice.dtos.RegisterResponseDto;
import com.indytskyi.userservice.models.User;


public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequestDto request);

    RegisterResponseDto register(RegisterRequestDto request);

    User validateToken(String bearerToken);
}
