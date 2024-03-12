package com.hanghae.study.domain.article.dto;

import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;

public class ArticleRequestDto {

    public record CreateArticleRequestDto(
            @NotBlank(message = "제목을 입력해 주세요.")
            String title,

            @NotBlank(message = "내용을 입력해 주세요.")
            String contents
    ) {

        public Article toEntity(Member member) {
            return Article.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .member(member)
                    .build();
        }
    }

    public record UpdateArticleRequestDto(
            @NotBlank(message = "제목을 입력해 주세요.")
            String title,

            @NotBlank(message = "내용을 입력해 주세요.")
            String contents
    ) {
    }
}
