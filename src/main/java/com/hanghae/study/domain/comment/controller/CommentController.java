package com.hanghae.study.domain.comment.controller;

import com.hanghae.study.domain.comment.controller.docs.CommentControllerDocs;
import com.hanghae.study.domain.comment.dto.CommentRequestDto.CreateCommentRequestDto;
import com.hanghae.study.domain.comment.dto.CommentRequestDto.EditCommentRequestDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.CreateCommentResponseDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.EditCommentResponseDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.GetCommentResponseDto;
import com.hanghae.study.domain.comment.service.CommentService;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CommentController implements CommentControllerDocs {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/articles/{articleId}/comments")
    public ResponseDto<CreateCommentResponseDto> createComment(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateCommentRequestDto requestDto
    ) {
        CreateCommentResponseDto responseDto = commentService.createComment(articleId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("댓글 생성 기능", responseDto);
    }

    @GetMapping("/articles/{articleId}/comments")
    public ResponseDto<List<GetCommentResponseDto>> getComments(
            @PathVariable Long articleId
    ) {
        List<GetCommentResponseDto> responseDto = commentService.getComments(articleId);
        return ResponseDto.success("댓글 목록 조회 기능", responseDto);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseDto<EditCommentResponseDto> editComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid EditCommentRequestDto requestDto
    ) {
        EditCommentResponseDto responseDto = commentService.editComment(commentId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("댓글 수정 기능", responseDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/comments/{commentId}")
    public ResponseDto<Void> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(commentId, userDetails.getUsername());
        return ResponseDto.success("댓글 삭제 기능", null);
    }
}
