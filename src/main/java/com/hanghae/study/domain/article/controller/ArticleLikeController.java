package com.hanghae.study.domain.article.controller;

import com.hanghae.study.domain.article.service.ArticleLikeService;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
public class ArticleLikeController {

    private final ArticleLikeService articleLikeService;

    public ArticleLikeController(ArticleLikeService articleLikeService) {
        this.articleLikeService = articleLikeService;
    }

    @PostMapping("/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDto<Void> switchingLike(@PathVariable Long articleId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        articleLikeService.swichingLike(articleId, userDetails.getUsername());
        return ResponseDto.success("좋아요 기능", null);
    }
}
