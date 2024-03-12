package com.hanghae.study.domain.comment.controller.docs;

import com.hanghae.study.domain.comment.dto.CommentRequestDto.CreateCommentRequestDto;
import com.hanghae.study.domain.comment.dto.CommentRequestDto.EditCommentRequestDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.CreateCommentResponseDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.EditCommentResponseDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.GetCommentResponseDto;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "comments", description = "댓글 관련 API")
public interface CommentControllerDocs {

    @Operation(summary = "댓글 생성 기능", description = "댓글을 생성할 수 있는 API")
    ResponseDto<CreateCommentResponseDto> createComment(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateCommentRequestDto requestDto
    );

    @Operation(summary = "댓글 목록 조회 기능", description = "댓글 목록을 조회할 수 있는 API")
    ResponseDto<List<GetCommentResponseDto>> getComments(
            @PathVariable Long articleId
    );

    @Operation(summary = "댓글 수정 기능", description = "댓글을 수정할 수 있는 API")
    ResponseDto<EditCommentResponseDto> editComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid EditCommentRequestDto requestDto
    );

    @Operation(summary = "댓글 삭제 기능", description = "댓글을 삭제할 수 있는 API")
    ResponseDto<Void> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );
}
