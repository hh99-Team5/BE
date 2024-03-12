package com.hanghae.study.domain.article.service;

import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.article.entity.ArticleLike;
import com.hanghae.study.domain.article.repository.ArticleLikeRepository;
import com.hanghae.study.domain.article.repository.ArticleRepository;
import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.domain.member.repository.MemberRepository;
import com.hanghae.study.global.exception.CustomApiException;
import com.hanghae.study.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleLikeService {

    private final ArticleLikeRepository articleLikeRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void switchingLike(Long articleId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_MEMBER.getMessage())
        );
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage())
        );

        articleLikeRepository.findByArticleAndMember(article, member).ifPresentOrElse(
                articleLikeRepository::delete,
                () -> articleLikeRepository.save(ArticleLike.builder()
                        .article(article)
                        .member(member)
                        .build())
        );
    }
}
