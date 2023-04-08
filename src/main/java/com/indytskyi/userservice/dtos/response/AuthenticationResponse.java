package com.indytskyi.userservice.dtos.response;

public record AuthenticationResponse(String token, String refreshToken) {
}