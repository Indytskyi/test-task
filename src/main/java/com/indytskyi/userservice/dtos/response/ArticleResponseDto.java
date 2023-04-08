package com.indytskyi.userservice.dtos.response;


import com.indytskyi.userservice.models.enums.Color;
import lombok.Builder;

@Builder
public record ArticleResponseDto(Long id, String text, Color color) { }
