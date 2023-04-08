package com.indytskyi.userservice.services.impl;

import com.indytskyi.userservice.dtos.request.ArticleRequestDto;
import com.indytskyi.userservice.dtos.response.ArticleResponseDto;
import com.indytskyi.userservice.models.Article;
import com.indytskyi.userservice.models.enums.Color;
import com.indytskyi.userservice.repository.ArticleRepository;
import com.indytskyi.userservice.services.ArticleService;
import com.indytskyi.userservice.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public ArticleResponseDto saveArticle(ArticleRequestDto articleRequestDto, String bearerToken) {
        log.info("token validation for correctness");
        var user = authenticationService.validateToken(bearerToken);

        log.info("save article to User with name = {}", user.getName());
        var article = Article.builder()
                .color(Color.valueOf(articleRequestDto.getColor()))
                .text(articleRequestDto.getText())
                .build();

        user.addArticle(article);
        article = articleRepository.save(article);

        return ArticleResponseDto.builder()
                .id(article.getId())
                .color(article.getColor())
                .text(article.getText())
                .build();
    }
}
