package com.indytskyi.userservice.dtos;


import com.indytskyi.userservice.models.enums.Color;
import lombok.Builder;

@Builder
public record ArticleResponseDto(Long id, String text, Color color) { }
