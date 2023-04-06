package com.indytskyi.userservice.dtos;

import lombok.Builder;

@Builder(toBuilder = true, builderMethodName = "of")
public record ValidateTokenResponseDto(String userEmail, String role) {
}