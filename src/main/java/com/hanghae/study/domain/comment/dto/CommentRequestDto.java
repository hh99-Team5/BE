package com.hanghae.study.domain.comment.dto;

import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.comment.entity.Comment;
import com.hanghae.study.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;

public class CommentRequestDto {

    public record CreateCommentRequestDto(
            @NotBlank(message = "내용을 입력해 주세요.")
            String contents
    ) {
        public Comment toEntity(Member member, Article article) {
            return Comment.builder()
                    .contents(contents)
                    .member(member)
                    .article(article)
                    .build();
        }
    }

    public record EditCommentRequestDto(
            String contents
    ) {

    }
}
