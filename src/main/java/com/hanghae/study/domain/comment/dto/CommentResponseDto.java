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
}
