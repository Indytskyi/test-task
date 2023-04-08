package com.indytskyi.userservice.repository;

import com.indytskyi.userservice.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
