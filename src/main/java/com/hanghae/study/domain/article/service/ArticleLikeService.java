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

@RequiredArgsConstructor
@Service
public class ArticleLikeService {

    private final ArticleLikeRepository articleLikeRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public boolean swichingLike(Long articleId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(()
                -> new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage()));
        Article article = articleRepository.findById(articleId).orElseThrow(()
                -> new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage()));
        if (article.getMember().equals(member)) {
            boolean isLiked = isLikedByMember(article, member);
            if (!isLiked) {
                ArticleLike like = new ArticleLike(member, article);
                article.getLikes().add(like);
                articleRepository.save(article);

                articleLikeRepository.save(like);

                return true;
            } else {
                ArticleLike like = findLikeMember(article, member);
                article.getLikes().remove(like);
                articleRepository.save(article);

                articleLikeRepository.delete(like);

                return false;
            }
        } else {
            throw new CustomApiException(ErrorCode.ARTICLE_NOT_MATCH_MEMBER.getMessage());
        }

    }

    private boolean isLikedByMember(Article article, Member member) {
        return article.getLikes().stream()
                .anyMatch(like -> like.getMember().equals(member));
    }

    private ArticleLike findLikeMember(Article article, Member member) {
        return article.getLikes().stream()
                .filter(like -> like.getMember().equals(member))
                .findFirst()
                .orElseThrow(() -> new CustomApiException(ErrorCode.LIKE_NOT_FOUND.getMessage()));
    }
}
