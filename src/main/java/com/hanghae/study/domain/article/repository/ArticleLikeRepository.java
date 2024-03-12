package com.hanghae.study.domain.article.repository;

import com.hanghae.study.domain.article.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
}
