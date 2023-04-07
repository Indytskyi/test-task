package com.indytskyi.userservice.services;

import com.indytskyi.userservice.dtos.ArticleRequestDto;
import com.indytskyi.userservice.dtos.ArticleResponseDto;
import com.indytskyi.userservice.models.Article;

public interface ArticleService {

    ArticleResponseDto saveArticle(ArticleRequestDto articleRequestDto, String bearerToken);
}
