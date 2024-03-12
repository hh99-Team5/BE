package com.hanghae.study.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanghae.study.domain.member.entity.Member;

import java.time.LocalDateTime;

public class MemberResponseDto {

    public record MemberSignupResponseDto(
            String email,
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime createdAt
    ) {
        public MemberSignupResponseDto(Member member) {
            this(
                    member.getEmail(),
                    member.getCreatedAt()
            );
        }
    }

    public record MemberSigninResponseDto(
            Long id
    ) {
        public MemberSigninResponseDto(Member member) {
            this(
                    member.getId()
            );
        }
    }
}
