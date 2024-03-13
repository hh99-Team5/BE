package com.hanghae.study.domain.article.repository;

import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByMemberOrderByCreatedAtDesc(Member member);
}
