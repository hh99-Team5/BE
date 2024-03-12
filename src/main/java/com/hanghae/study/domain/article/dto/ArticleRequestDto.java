package com.hanghae.study.domain.article.dto;

import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

public class ArticleRequestDto {

    public record CreateArticleRequestDto(
        @NotBlank(message = "제목을 입력해 주세요.")
        String title,
        @NotBlank(message = "내용을 입력해 주세요.")
        String contents){

        public Article toEntity(Member member) {
            return Article.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .member(member)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }
    }

    public record UpdateArticleRequestDto (
        @NotBlank(message = "제목을 입력해 주세요.")
        String title,
        @NotBlank(message = "내용을 입력해 주세요.")
        String contents){

    }

//    @Getter
//    public static class CreateArticleRequestDto {
//        private final Member writer;
//        @NotBlank(message = "제목을 입력해 주세요.")
//        private final String title;
//        @NotBlank(message = "내용을 입력해 주세요.")
//        private final String contents;
//
//        public CreateArticleRequestDto(Member writer, String title, String contents) {
//            this.writer = writer;
//            this.title = title;
//            this.contents = contents;
//        }
//
//        public Article toEntity() {
//            return Article.builder()
//                    .member(this.writer)
//                    .title(this.title)
//                    .contents(this.contents)
//                    .build();
//        }
//    }
//
//    @Getter
//    public static class UpdateArticleRequestDto {
//        @NotBlank(message = "제목을 입력해 주세요.")
//        private final String title;
//        @NotBlank(message = "내용을 입력해 주세요.")
//        private final String contents;
//
//        public UpdateArticleRequestDto(String title, String contents) {
//            this.title = title;
//            this.contents = contents;
//        }
//    }
}
