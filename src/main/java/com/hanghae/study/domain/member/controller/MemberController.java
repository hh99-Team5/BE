package com.hanghae.study.domain.member.controller;

import com.hanghae.study.domain.member.controller.docs.MemberControllerDocs;
import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberCheckEmailRequestDto;
import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberSignupRequestDto;
import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberUpdateRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberCheckEmailResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberSignupResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberUpdateResponseDto;
import com.hanghae.study.domain.member.service.MemberService;
import com.hanghae.study.global.dto.ResponseDto;
import com.hanghae.study.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseDto<MemberSignupResponseDto> signup(
            @RequestBody @Valid MemberSignupRequestDto requestDto
    ) {
        MemberSignupResponseDto responseDto = memberService.signup(requestDto);
        return ResponseDto.success("회원가입 기능", responseDto);
    }

    @PutMapping
    public ResponseDto<MemberUpdateResponseDto> editMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid MemberUpdateRequestDto requestDto
    ) {
        MemberUpdateResponseDto responseDto = memberService.editMember(userDetails.getUsername(), requestDto);
        return ResponseDto.success("회원 정보 수정 기능", responseDto);
    }

    @GetMapping("/check-email")
    public ResponseDto<MemberCheckEmailResponseDto> checkEmail(
            @RequestParam String email
    ) {
        MemberCheckEmailResponseDto responseDto = memberService.checkEmail(email);
        return ResponseDto.success("회원 이메일 중복 검사", responseDto);
    }
}
