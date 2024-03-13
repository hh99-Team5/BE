package com.hanghae.study.domain.article.service;

import com.hanghae.study.domain.article.dto.ArticleResponseDto.CreateArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.EditArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.GetArticleResponseDto;
import com.hanghae.study.domain.article.dto.ArticleResponseDto.SearchArticleResponseDto;
import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.article.repository.ArticleLikeRepository;
import com.hanghae.study.domain.article.repository.ArticleRepository;
import com.hanghae.study.domain.article.repository.ArticleSearchRepository;
import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.domain.member.repository.MemberRepository;
import com.hanghae.study.global.exception.CustomApiException;
import com.hanghae.study.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hanghae.study.domain.article.dto.ArticleRequestDto.CreateArticleRequestDto;
import static com.hanghae.study.domain.article.dto.ArticleRequestDto.EditArticleRequestDto;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleLikeRepository articleLikeRepository;
    private final ArticleSearchRepository articleSearchRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateArticleResponseDto createArticle(String email, CreateArticleRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage())
        );

        Article article = articleRepository.save(requestDto.toEntity(member));
        return new CreateArticleResponseDto(article, member.getEmail());
    }

    public GetArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage())
        );

        Long like = articleLikeRepository.countByArticle(article);
        return new GetArticleResponseDto(article, like);
    }

    public List<SearchArticleResponseDto> getArticles() {
        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(article -> {
                    Long like = articleLikeRepository.countByArticle(article);
                    return new SearchArticleResponseDto(article, like);
                })
                .toList();
    }

    @Transactional
    public EditArticleResponseDto editArticle(Long articleId, String email, EditArticleRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage())
        );
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage())
        );
        if (article.getMember() != member) {
            throw new CustomApiException(ErrorCode.NOT_MATCH_ARTICLE_MEMBER.getMessage());
        }

        article.update(requestDto.title(), requestDto.contents());
        return new EditArticleResponseDto(article);
    }

    @Transactional
    public void deleteArticle(Long articleId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage())
        );
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage())
        );
        if (article.getMember() != member) {
            throw new CustomApiException(ErrorCode.NOT_MATCH_ARTICLE_MEMBER.getMessage());
        }

        articleRepository.delete(article);
    }

    @Transactional(readOnly = true)
    public List<SearchArticleResponseDto> searchArticles(String type, String keyword) {
        return articleSearchRepository.searchArticle(type, keyword).stream()
                .map(article -> {
                    Long like = articleLikeRepository.countByArticle(article);
                    return new SearchArticleResponseDto(article, like);
                })
                .toList();

    }
}
