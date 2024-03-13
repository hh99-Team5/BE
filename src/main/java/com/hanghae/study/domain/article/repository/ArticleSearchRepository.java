package com.hanghae.study.domain.article.repository;

import com.hanghae.study.domain.article.entity.Article;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hanghae.study.domain.article.entity.QArticle.article;

@RequiredArgsConstructor
@Repository
public class ArticleSearchRepository {

    private final JPQLQueryFactory jpqlQueryFactory;

    public List<Article> searchArticle(String type, String keyword) {
        return jpqlQueryFactory.selectFrom(article)
                .where(
                        equalsType(type, keyword)
                )
                .orderBy(article.createdAt.desc())
                .fetch();
    }

    private BooleanExpression equalsType(String type, String keyword) {
        return switch (type) {
            case "contents" -> article.contents.contains(keyword);
            case "writer" -> article.member.email.contains(keyword);
            default -> article.title.contains(keyword);
        };
    }
}
