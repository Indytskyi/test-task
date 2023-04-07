package com.indytskyi.userservice.dtos;

import jakarta.validation.constraints.Email;

public record AuthenticationRequestDto(
        String email, String password) {
}
