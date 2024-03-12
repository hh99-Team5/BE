package com.hanghae.study.domain.article.service;

import com.hanghae.study.domain.article.dto.ArticleRequestDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.CreateArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.GetArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.UpdateArticleResponseDto;
import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.article.repository.ArticleLikeRepository;
import com.hanghae.study.domain.article.repository.ArticleRepository;
import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.domain.member.repository.MemberRepository;
import com.hanghae.study.global.exception.CustomApiException;
import com.hanghae.study.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hanghae.study.domain.article.dto.ArticleRequestDto.*;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleLikeRepository articleLikeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateArticleResponseDto createArticle(CreateArticleRequestDto requestDto, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(()
                -> new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage()));
        Article article = articleRepository.save(requestDto.toEntity(member));
        return new CreateArticleResponseDto(article,email);
    }

    @Transactional(readOnly = true)
    public GetArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage())
        );
        Long likes = articleLikeRepository.countByArticle(article);
        return new GetArticleResponseDto(article, likes);
    }

    @Transactional(readOnly = true)
    public List<GetArticleResponseDto> getArticles() {
        List<Article> articleList = articleRepository.findAll();


        return articleList.stream().map(article ->
                GetArticleResponseDto.from(article, (long) article.getLikes().size())
        ).collect(Collectors.toList());
    }

    @Transactional
    public UpdateArticleResponseDto updateArticle(Long articleId,
                                                  String email,
                                                  UpdateArticleRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(()
                -> new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage()));
        Article article = articleRepository.findById(articleId).orElseThrow(()
                -> new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage()));
        if(article.getMember() != member){
            throw new CustomApiException(ErrorCode.ARTICLE_NOT_MATCH_MEMBER.getMessage());
        }
        article.update(requestDto.title(), requestDto.contents());
        return new UpdateArticleResponseDto(article);
    }

    @Transactional
    public void delete(Long articleId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(()
                -> new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage()));
        Article article = articleRepository.findById(articleId).orElseThrow(()
                -> new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage()));
        if (article.getMember() != member) {
            throw new CustomApiException(ErrorCode.NOT_FOUND_MEMBER.getMessage());
        }
        articleRepository.delete(article);
    }
}
