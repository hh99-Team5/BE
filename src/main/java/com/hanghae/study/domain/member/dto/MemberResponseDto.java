package com.hanghae.study.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanghae.study.domain.member.entity.Member;

import java.time.LocalDateTime;

public class MemberResponseDto {

    public record SignupMemberResponseDto(
            String email,
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime createdAt
    ) {
        public SignupMemberResponseDto(Member member) {
            this(
                    member.getEmail(),
                    member.getCreatedAt()
            );
        }
    }

    public record SigninMemberResponseDto(
            Long id
    ) {
        public SigninMemberResponseDto(Member member) {
            this(
                    member.getId()
            );
        }
    }

    public record EditMemberResponseDto(
            Long id
    ) {
        public EditMemberResponseDto(Member member) {
            this(
                    member.getId()
            );
        }
    }

    public record CheckMemberEmailResponseDto(
            Boolean isExist
    ) {

    }

    public record GetMemberResponseDto(
            Long id,
            String email,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            LocalDateTime createdAt
    ) {
        public GetMemberResponseDto(Member member) {
            this(
                    member.getId(),
                    member.getEmail(),
                    member.getCreatedAt()
            );
        }
    }
}
