package com.hanghae.study.domain.member.controller.docs;

import com.hanghae.study.domain.article.dto.ArticleResponseDto;
import com.hanghae.study.domain.member.dto.MemberRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.CheckMemberEmailResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.EditMemberResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.SignupMemberResponseDto;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "members", description = "회원 관련 API")
public interface MemberControllerDocs {

    @Operation(summary = "회원 가입 기능", description = "회원 가입할 수 있는 API")
    ResponseDto<SignupMemberResponseDto> signup(
            @RequestBody @Valid MemberRequestDto.SignupMemberRequestDto requestDto
    );

    @Operation(summary = "회원 정보 수정 기능", description = "회원 정보를 수정할 수 있는 API")
    ResponseDto<EditMemberResponseDto> editMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid MemberRequestDto.EditMemberRequestDto requestDto
    );

    @Operation(summary = "회원 이메일 중복 검사 기능", description = "가입된 회원 이메일을 중복 검사할 수 있는 API")
    ResponseDto<CheckMemberEmailResponseDto> checkEmail(
            @RequestParam String email
    );

    @Operation(summary = "회원 정보 조회 기능", description = "가입된 회원 정보를 조회할 수 있는 API")
    ResponseDto<MemberResponseDto.GetMemberResponseDto> getMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(summary = "회원 작성 일지 목록 조회 기능", description = "가입된 회원이 작성한 일지를 조회할 수 있는 API")
    ResponseDto<List<ArticleResponseDto.SearchArticleResponseDto>> getMemberArticles(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );
}
