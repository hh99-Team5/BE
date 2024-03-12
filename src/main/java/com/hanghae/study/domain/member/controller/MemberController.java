package com.hanghae.study.domain.member.controller;

import com.hanghae.study.domain.member.controller.docs.MemberControllerDocs;
import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberSignupRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberSignupResponseDto;
import com.hanghae.study.domain.member.service.MemberService;
import com.hanghae.study.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
}
