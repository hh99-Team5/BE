package com.hanghae.study.domain.member.controller.docs;

import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberCheckEmailRequestDto;
import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberSignupRequestDto;
import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberUpdateRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberCheckEmailResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberSignupResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberUpdateResponseDto;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "members", description = "회원 관련 API")
public interface MemberControllerDocs {

    @Operation(summary = "회원 가입 기능", description = "회원 가입할 수 있는 API")
    ResponseDto<MemberSignupResponseDto> signup(
            @RequestBody @Valid MemberSignupRequestDto requestDto
    );

    @Operation(summary = "회원 정보 수정 기능", description = "회원 정보를 수정할 수 있는 API")
    ResponseDto<MemberUpdateResponseDto> editMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid MemberUpdateRequestDto requestDto
    );

    @Operation(summary = "회원 이메일 중복 검사 기능", description = "가입된 회원 이메일을 중복 검사할 수 있는 API")
    ResponseDto<MemberCheckEmailResponseDto> checkEmail(
            @RequestBody @Valid MemberCheckEmailRequestDto requestDto
    );
}
