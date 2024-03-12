package com.hanghae.study.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanghae.study.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {

    public record CreateCommentResponseDto(
            Long id,
            String contents,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime createdAt
    ) {
        public CreateCommentResponseDto(Comment comment) {
            this(
                    comment.getId(),
                    comment.getContents(),
                    comment.getCreatedAt()
            );
        }
    }

    public record GetCommentResponseDto(
            Long id,
            String writer,
            String contents,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime createdAt,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime updatedAt
    ) {
        public GetCommentResponseDto(Comment comment) {
            this(
                    comment.getId(),
                    comment.getMember().getEmail(),
                    comment.getContents(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            );
        }
    }

    public record EditCommentResponseDto(
            Long id,
            String contents
    ) {
        public EditCommentResponseDto(Comment comment) {
            this(
                    comment.getId(),
                    comment.getContents()
            );
        }
    }
}
