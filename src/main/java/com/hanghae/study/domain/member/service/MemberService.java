package com.hanghae.study.domain.member.service;

import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberCheckEmailRequestDto;
import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberSignupRequestDto;
import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberUpdateRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberCheckEmailResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberSignupResponseDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberUpdateResponseDto;
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
    public MemberSignupResponseDto signup(MemberSignupRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.email())) {
            throw new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage());
        }

        String encodedPassword = passwordEncoder.encode(requestDto.password());
        Member member = memberRepository.save(requestDto.toEntity(encodedPassword));
        return new MemberSignupResponseDto(member);
    }

    @Transactional
    public MemberUpdateResponseDto editMember(String email, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_MEMBER.getMessage())
        );

        member.updatePassword(passwordEncoder.encode(requestDto.password()));
        return new MemberUpdateResponseDto(member);
    }

    public MemberCheckEmailResponseDto checkEmail(String email) {
        return new MemberCheckEmailResponseDto(memberRepository.existsByEmail(email));
    }
}
