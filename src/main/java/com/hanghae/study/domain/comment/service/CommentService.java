package com.hanghae.study.domain.comment.service;

import com.hanghae.study.domain.article.entity.Article;
import com.hanghae.study.domain.article.repository.ArticleRepository;
import com.hanghae.study.domain.comment.dto.CommentRequestDto.CreateCommentRequestDto;
import com.hanghae.study.domain.comment.dto.CommentRequestDto.EditCommentRequestDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.CreateCommentResponseDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.EditCommentResponseDto;
import com.hanghae.study.domain.comment.dto.CommentResponseDto.GetCommentResponseDto;
import com.hanghae.study.domain.comment.entity.Comment;
import com.hanghae.study.domain.comment.repository.CommentRepository;
import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.domain.member.repository.MemberRepository;
import com.hanghae.study.global.exception.CustomApiException;
import com.hanghae.study.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public CreateCommentResponseDto createComment(Long articleId, String email, CreateCommentRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_MEMBER.getMessage())
        );
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage())
        );

        Comment comment = commentRepository.save(requestDto.toEntity(member, article));
        return new CreateCommentResponseDto(comment);
    }

    public List<GetCommentResponseDto> getComments(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_ARTICLE.getMessage())
        );

        return commentRepository.findAllByArticle(article).stream()
                .map(GetCommentResponseDto::new)
                .toList();
    }

    @Transactional
    public EditCommentResponseDto editComment(Long commentId, String email, EditCommentRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_MEMBER.getMessage())
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_COMMENT.getMessage())
        );
        if (!comment.getMember().equals(member)) {
            throw new CustomApiException(ErrorCode.NOT_MATCH_COMMENT_MEMBER.getMessage());
        }

        comment.update(requestDto.contents());
        return new EditCommentResponseDto(comment);
    }
}
