package com.hanghae.study.domain.member.controller;

import com.hanghae.study.domain.member.controller.docs.MemberControllerDocs;
import com.hanghae.study.domain.member.dto.MemberRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.CheckMemberEmailResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.EditMemberResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.GetMemberResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.SignupMemberResponseDto;
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
    public ResponseDto<SignupMemberResponseDto> signup(
            @RequestBody @Valid MemberRequestDto.SignupMemberRequestDto requestDto
    ) {
        SignupMemberResponseDto responseDto = memberService.signup(requestDto);
        return ResponseDto.success("회원가입 기능", responseDto);
    }

    @PutMapping
    public ResponseDto<EditMemberResponseDto> editMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid MemberRequestDto.EditMemberRequestDto requestDto
    ) {
        EditMemberResponseDto responseDto = memberService.editMember(userDetails.getUsername(), requestDto);
        return ResponseDto.success("회원 정보 수정 기능", responseDto);
    }

    @GetMapping("/email-check")
    public ResponseDto<CheckMemberEmailResponseDto> checkEmail(
            @RequestParam String email
    ) {
        CheckMemberEmailResponseDto responseDto = memberService.checkEmail(email);
        return ResponseDto.success("회원 이메일 중복 검사", responseDto);
    }

    @GetMapping
    public ResponseDto<GetMemberResponseDto> getMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        GetMemberResponseDto responseDto = memberService.getMember(userDetails.getUsername());
        return ResponseDto.success("회원 정보 조회 기능", responseDto);
    }
}
