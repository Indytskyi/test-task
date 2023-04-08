package com.indytskyi.userservice.service;

import com.indytskyi.userservice.dto.request.ArticleRequestDto;
import com.indytskyi.userservice.dto.response.ArticleResponseDto;

public interface ArticleService {

    ArticleResponseDto saveArticle(ArticleRequestDto articleRequestDto, String bearerToken);
}
