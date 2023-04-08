package com.indytskyi.userservice.services;

import com.indytskyi.userservice.dtos.request.ArticleRequestDto;
import com.indytskyi.userservice.dtos.response.ArticleResponseDto;

public interface ArticleService {

    ArticleResponseDto saveArticle(ArticleRequestDto articleRequestDto, String bearerToken);
}
