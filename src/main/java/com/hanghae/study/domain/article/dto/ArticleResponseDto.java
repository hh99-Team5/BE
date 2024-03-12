package com.hanghae.study.domain.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.GetCommentResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDto {

    public record CreateArticleResponseDto(
            Long id,
            String writer,
            String title,
            String contents,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime createdAt
    ) {
        public CreateArticleResponseDto(Article article, String email) {
            this(
                    article.getId(),
                    email,
                    article.getTitle(),
                    article.getContents(),
                    article.getCreatedAt()
            );
        }
    }

    public record GetArticleResponseDto(
            Long id,
            String writer,
            String title,
            String contents,
            Long like,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime createdAt,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime updatedAt,

            List<GetCommentResponseDto> comments
    ) {
        public GetArticleResponseDto(Article article, Long like) {
            this(
                    article.getId(),
                    article.getMember().getEmail(),
                    article.getTitle(),
                    article.getContents(),
                    like,
                    article.getCreatedAt(),
                    article.getUpdatedAt(),
                    GetCommentResponseDto.listOf(article.getComments())
            );
        }
    }

    public record EditArticleResponseDto(
            Long id,
            String writer,
            String title,
            String contents,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime createdAt
    ) {
        public EditArticleResponseDto(Article article) {
            this(
                    article.getId(),
                    article.getMember().getEmail(),
                    article.getTitle(),
                    article.getContents(),
                    article.getCreatedAt()
            );
        }
    }
}
