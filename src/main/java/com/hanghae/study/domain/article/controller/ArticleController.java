package com.hanghae.study.domain.article.controller;

import com.hanghae.study.domain.article.service.ArticleLikeService;
import com.hanghae.study.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleLikeService articleLikeService;
}
