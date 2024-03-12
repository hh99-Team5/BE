package com.hanghae.study.domain.member.service;

import com.hanghae.study.domain.member.dto.MemberRequestDto.MemberSignupRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto.MemberSignupResponseDto;
import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.domain.member.repository.MemberRepository;
import com.hanghae.study.global.exception.CustomApiException;
import com.hanghae.study.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSignupResponseDto signup(MemberSignupRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.email())) {
            throw new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage());
        }

        Member member = memberRepository.save(requestDto.toEntity());
        return new MemberSignupResponseDto(member);
    }
}
