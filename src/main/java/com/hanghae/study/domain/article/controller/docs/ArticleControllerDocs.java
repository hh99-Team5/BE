package com.hanghae.study.domain.article.controller.docs;

import com.hanghae.study.domain.article.dto.ArticleRequestDto;
import com.hanghae.study.domain.article.dto.ArticleRequestDto.CreateArticleRequestDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.CreateArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.GetArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.SearchArticleResponseDto;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "articles", description = "일지 관련 API")
public interface ArticleControllerDocs {

    @Operation(summary = "일지 생성 기능", description = "일지를 생성할 수 있는 API")
    ResponseDto<CreateArticleResponseDto> createArticle(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateArticleRequestDto requestDto
    );

    @Operation(summary = "일지 조회 기능", description = "일지를 조회할 수 있는 API")
    ResponseDto<GetArticleResponseDto> getArticle(
            @PathVariable Long articleId
    );

    @Operation(summary = "일지 목록 조회 기능", description = "일지 목록을 조회할 수 있는 API")
    ResponseDto<List<SearchArticleResponseDto>> getArticles(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(summary = "일지 수정 기능", description = "일지를 수정할 수 있는 API")
    ResponseDto<ArticleResponseDto.EditArticleResponseDto> editArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid ArticleRequestDto.EditArticleRequestDto requestDto
    );

    @Operation(summary = "일지 삭제 기능", description = "일지를 삭제할 수 있는 API")
    ResponseDto<Void> deleteArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(summary = "일지 좋아요 기능", description = "일지를 좋아요할 수 있는 API")
    ResponseDto<Void> switchingArticleLike(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(summary = "일지 검색 기능", description = "일지를 검색할 수 있는 API")
    ResponseDto<List<SearchArticleResponseDto>> searchArticles(
            @RequestParam(defaultValue = "title") String type,
            @RequestParam String keyword
    );

    @Operation(summary = "일지 좋아요 확인 기능", description = "일지 좋아요를 확인할 수 있는 API")
    ResponseDto<ArticleResponseDto.CheckArticleLikeResponseDto> checkLikeArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );
}
