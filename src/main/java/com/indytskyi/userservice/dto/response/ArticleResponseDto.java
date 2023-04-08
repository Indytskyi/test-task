package com.indytskyi.userservice.dto.response;


import com.indytskyi.userservice.model.enums.Color;
import lombok.Builder;

@Builder
public record ArticleResponseDto(Long id, String text, Color color) { }
