package com.hanghae.study.domain.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanghae.study.domain.article.entity.Article;

import java.time.LocalDateTime;

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
            Long likes,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt) {

        public GetArticleResponseDto(Article article, Long likes) {
            this(article.getId(),
                    article.getMember().getEmail(),
                    article.getTitle(),
                    article.getContents(),
                    likes,
                    article.getCreatedAt(),
                    article.getUpdatedAt()
            );
        }

        public static GetArticleResponseDto from(Article article, Long likes) {
            return new GetArticleResponseDto(
                    article.getId(),
                    article.getMember().getEmail(),
                    article.getTitle(),
                    article.getContents(),
                    likes,
                    article.getCreatedAt(),
                    article.getUpdatedAt()
            );
        }
    }

    public record UpdateArticleResponseDto(
            Long id,
            String writer,
            String title,
            String contents,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt) {

        public UpdateArticleResponseDto(Article article) {
            this(article.getId(),
                    article.getMember().getEmail(),
                    article.getTitle(),
                    article.getContents(),
                    article.getCreatedAt(),
                    article.getUpdatedAt()
            );
        }


    }

//    @Getter
//    public static class CreateArticleResponseDto {
//        private Long id;
//        private String writer;
//        private String title;
//        private String contents;
//        private LocalDateTime createdAt;
//
//        public CreateArticleResponseDto(Article article) {
//            this.id = article.getId();
//            this.writer = article.getMember().getEmail();
//            this.title = article.getTitle();
//            this.contents = article.getContents();
//            this.createdAt = LocalDateTime.now();
//        }
//    }
//
//    @Getter
//    public static class GetArticleResponseDto {
//        private Long id;
//        private String writer;
//        private String title;
//        private String contents;
//        private Long likes;
//        private LocalDateTime createdAt;
//        private LocalDateTime modifiedAt;
//
//        public GetArticleResponseDto(Article article, Long likes) {
//            this.id = article.getId();
//            this.writer = article.getMember().getEmail();
//            this.title = article.getTitle();
//            this.contents = article.getContents();
//            this.likes = likes;
//        }
//    }
//
//    @Getter
//    public static class UpdateArticleResponseDto {
//        private Long id;
//        private String writer;
//        private String title;
//        private String contents;
//        private LocalDateTime createdAt;
//        private LocalDateTime modifiedAt;
//
//        public UpdateArticleResponseDto(Article article) {
//            this.id = article.getId();
//            this.writer = article.getMember().getEmail();
//            this.title = article.getTitle();
//            this.contents = article.getContents();
//            this.createdAt = article.getCreatedAt();
//            this.modifiedAt = article.getModifiedAt();
//        }
//
//
//    }
}
