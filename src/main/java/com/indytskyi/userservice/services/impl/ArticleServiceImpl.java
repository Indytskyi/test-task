package com.indytskyi.userservice.services.impl;

import static com.indytskyi.userservice.models.enums.Role.ADMIN;

import com.indytskyi.userservice.dtos.ArticleRequestDto;
import com.indytskyi.userservice.dtos.ArticleResponseDto;
import com.indytskyi.userservice.exception.LimitedPermissionException;
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
    public ArticleResponseDto saveArticle(ArticleRequestDto articleRequestDto, String bearerToken) {
        var user = authenticationService.validateToken(bearerToken);
       
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
