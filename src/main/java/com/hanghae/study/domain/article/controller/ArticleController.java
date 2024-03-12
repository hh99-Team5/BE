package com.hanghae.study.domain.article.controller;

import com.hanghae.study.domain.article.dto.ArticleRequestDto;
import com.hanghae.study.domain.article.dto.ArticleRequestDto.UpdateArticleRequestDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto;
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

import static com.hanghae.study.domain.article.dto.ArticleResponseDto.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleLikeService articleLikeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateArticleResponseDto> createArticle(@RequestBody @Valid ArticleRequestDto.CreateArticleRequestDto requestDto,
                                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CreateArticleResponseDto responseDto = articleService.createArticle(requestDto, userDetails.getUsername());
        return ResponseDto.success("일지 생성 기능", responseDto);
    }

    @GetMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<GetArticleResponseDto> getArticle(@PathVariable Long articleId) {
        GetArticleResponseDto responseDto = articleService.getArticle(articleId);
        return ResponseDto.success("일지 조회 기능", responseDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<GetArticleResponseDto>> getArticles() {
        List<GetArticleResponseDto> responseDto = articleService.getArticles();
        return ResponseDto.success("일지 목록 조회 기능", responseDto);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{articleId}")
    public ResponseDto<UpdateArticleResponseDto> updateArticle(@PathVariable Long articleId,
                                                               @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                               @RequestBody @Valid UpdateArticleRequestDto requestDto) {
        UpdateArticleResponseDto responseDto = articleService.updateArticle(articleId, userDetails.getUsername(), requestDto);
        return ResponseDto.success("일지 수정 기능", responseDto);
    }

    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDto<Void> delete(@PathVariable Long articleId,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        articleService.delete(articleId, userDetails.getUsername());
        return ResponseDto.success("일지 삭제 기능", null);
    }

}
