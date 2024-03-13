package com.hanghae.study.domain.member.service;

import com.hanghae.study.domain.member.dto.MemberRequestDto.EditMemberRequestDto;
import com.hanghae.study.domain.member.dto.MemberRequestDto.SignupMemberRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.CheckMemberEmailResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.EditMemberResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.GetMemberResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.SignupMemberResponseDto;
import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.domain.member.repository.MemberRepository;
import com.hanghae.study.global.exception.CustomApiException;
import com.hanghae.study.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupMemberResponseDto signup(SignupMemberRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.email())) {
            throw new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage());
        }

        String encodedPassword = passwordEncoder.encode(requestDto.password());
        Member member = memberRepository.save(requestDto.toEntity(encodedPassword));
        return new SignupMemberResponseDto(member);
    }

    @Transactional
    public EditMemberResponseDto editMember(String email, EditMemberRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_MEMBER.getMessage())
        );

        member.updatePassword(passwordEncoder.encode(requestDto.password()));
        return new EditMemberResponseDto(member);
    }

    public CheckMemberEmailResponseDto checkEmail(String email) {
        return new CheckMemberEmailResponseDto(memberRepository.existsByEmail(email));
    }

    public GetMemberResponseDto getMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_MEMBER.getMessage())
        );
        return new GetMemberResponseDto(member);
    }
}
