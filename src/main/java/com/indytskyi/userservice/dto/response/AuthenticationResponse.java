package com.indytskyi.userservice.dto.response;

public record AuthenticationResponse(String token, String refreshToken) {
}