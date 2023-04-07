package com.indytskyi.userservice.dtos;

import com.indytskyi.userservice.models.Article;
import java.util.List;
import lombok.Builder;

@Builder
public record UserResponseDto(Long id, String email, String name, Integer age, List<Article> articles) {
}
