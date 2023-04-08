package com.indytskyi.userservice.dto.response;

import lombok.Builder;

@Builder(toBuilder = true, builderMethodName = "of")
public record ValidateTokenResponseDto(String userEmail, String role) { }