package com.hanghae.study.domain.article.service;

import com.hanghae.study.domain.article.repository.ArticleLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleLikeService {

    private final ArticleLikeRepository articleLikeRepository;
}
