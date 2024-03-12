package com.hanghae.study.domain.article.dto;

import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class ArticleRequestDto {

    public record CreateArticleRequestDto(
            @Schema(description = "제목", example = "오늘은 스프링 공부!")
            @NotBlank(message = "제목을 입력해 주세요.")
            String title,

            @Schema(description = "내용", example = "너무 어려워요..")
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

    public record EditArticleRequestDto(
            @Schema(description = "제목", example = "오늘은 리액트 공부")
            String title,

            @Schema(description = "내용", example = "너무 재밌어요~")
            String contents
    ) {
    }
}
