package com.indytskyi.userservice.services.impl;

import com.indytskyi.userservice.dtos.ArticleRequestDto;
import com.indytskyi.userservice.models.Article;
import com.indytskyi.userservice.models.enums.Color;
import com.indytskyi.userservice.repository.ArticleRepository;
import com.indytskyi.userservice.services.ArticleService;
import com.indytskyi.userservice.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthenticationService authenticationService;


    @Override
    @Transactional
    public Article saveArticle(ArticleRequestDto articleRequestDto, String bearerToken) {
        var user = authenticationService.validateToken(bearerToken);
        var article = Article.builder()
                .color(Color.valueOf(articleRequestDto.getColor()))
                .text(articleRequestDto.getText())
                .build();
        user.addArticle(article);
        return articleRepository.save(article);
    }
}
