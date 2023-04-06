package com.indytskyi.userservice.services.impl;

import com.indytskyi.userservice.dtos.ArticleRequestDto;
import com.indytskyi.userservice.models.Article;
import com.indytskyi.userservice.models.enums.Color;
import com.indytskyi.userservice.repository.ArticleRepository;
import com.indytskyi.userservice.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;


    @Override
    public Article saveArticle(ArticleRequestDto articleRequestDto) {
        var article = Article.builder()
                .color(Color.valueOf(articleRequestDto.getColor()))
                .text(articleRequestDto.getText())
                .build();
        return articleRepository.save(article);
    }
}
