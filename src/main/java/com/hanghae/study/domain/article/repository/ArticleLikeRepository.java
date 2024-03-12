package com.hanghae.study.domain.article.repository;

import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.article.entity.ArticleLike;
import com.hanghae.study.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {

    Optional<ArticleLike> findByArticleAndMember(Article article, Member member);

    Long countByArticle(Article article);
}
