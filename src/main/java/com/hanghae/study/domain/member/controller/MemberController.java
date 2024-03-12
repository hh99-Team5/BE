package com.hanghae.study.domain.member.controller;

import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberSignupRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberSignupResponseDto;
import com.hanghae.study.domain.member.service.MemberService;
import com.hanghae.study.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseDto<MemberSignupResponseDto> signup(
            @RequestBody @Valid MemberSignupRequestDto requestDto
    ) {
        MemberSignupResponseDto responseDto = memberService.signup(requestDto);
        return ResponseDto.success("회원가입 기능", responseDto);
    }
}
