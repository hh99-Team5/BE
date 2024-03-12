package com.hanghae.study.domain.comment.controller;

import com.hanghae.study.domain.comment.dto.CommentRequestDto.CreateCommentRequestDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.CreateCommentResponseDto;
import com.hanghae.study.domain.comment.service.CommentService;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/articles/{articleId}/comments")
    public ResponseDto<CreateCommentResponseDto> createComment(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateCommentRequestDto requestDto
    ) {
        CreateCommentResponseDto responseDto = commentService.createComment(articleId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("댓글 생성 기능", responseDto);
    }
}
