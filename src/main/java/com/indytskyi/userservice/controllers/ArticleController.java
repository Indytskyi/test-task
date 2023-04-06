package com.indytskyi.userservice.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import com.indytskyi.userservice.dtos.ArticleRequestDto;
import com.indytskyi.userservice.models.Article;
import com.indytskyi.userservice.services.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody @Valid ArticleRequestDto articleRequestDto) {
        return ResponseEntity
                .status(CREATED)
                .body(articleService.saveArticle(articleRequestDto));
    }
}
