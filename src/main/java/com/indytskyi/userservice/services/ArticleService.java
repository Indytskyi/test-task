package com.indytskyi.userservice.services;

import com.indytskyi.userservice.dtos.ArticleRequestDto;
import com.indytskyi.userservice.models.Article;

public interface ArticleService {

    Article saveArticle(ArticleRequestDto articleRequestDto);
}
