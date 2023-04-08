package com.indytskyi.userservice.dto.response;

import com.indytskyi.userservice.model.Article;
import java.util.List;
import lombok.Builder;

@Builder
public record UserResponseDto(Long id, String email, String name, Integer age, List<Article> articles) { }
