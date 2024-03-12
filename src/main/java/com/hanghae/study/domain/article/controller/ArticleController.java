package com.hanghae.study.domain.article.controller;

import com.hanghae.study.domain.article.dto.ArticleRequestDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.GetArticleResponseDto;
import com.hanghae.study.domain.article.service.ArticleLikeService;
import com.hanghae.study.domain.article.service.ArticleService;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hanghae.study.domain.article.dto.ArticleResponseDto.CreateArticleResponseDto;
import static com.hanghae.study.domain.article.dto.ArticleResponseDto.EditArticleResponseDto;

@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleLikeService articleLikeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseDto<CreateArticleResponseDto> createArticle(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid ArticleRequestDto.CreateArticleRequestDto requestDto
    ) {
        CreateArticleResponseDto responseDto = articleService.createArticle(userDetails.getUsername(), requestDto);
        return ResponseDto.success("일지 생성 기능", responseDto);
    }

    @GetMapping("/{articleId}")
    public ResponseDto<GetArticleResponseDto> getArticle(
            @PathVariable Long articleId
    ) {
        GetArticleResponseDto responseDto = articleService.getArticle(articleId);
        return ResponseDto.success("일지 조회 기능", responseDto);
    }

    @GetMapping
    public ResponseDto<List<GetArticleResponseDto>> getArticles() {
        List<GetArticleResponseDto> responseDto = articleService.getArticles();
        return ResponseDto.success("일지 목록 조회 기능", responseDto);
    }


    @PutMapping("/{articleId}")
    public ResponseDto<EditArticleResponseDto> editArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid ArticleRequestDto.EditArticleRequestDto requestDto
    ) {
        EditArticleResponseDto responseDto = articleService.editArticle(articleId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("일지 수정 기능", responseDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{articleId}")
    public ResponseDto<Void> deleteArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        articleService.delete(articleId, userDetails.getUsername());
        return ResponseDto.success("일지 삭제 기능", null);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{articleId}/likes")
    public ResponseDto<Void> switchingLike(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        articleLikeService.swichingLike(articleId, userDetails.getUsername());
        return ResponseDto.success("좋아요 기능", null);
    }
}
