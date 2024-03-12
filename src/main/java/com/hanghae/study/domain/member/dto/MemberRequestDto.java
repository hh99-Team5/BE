package com.hanghae.study.domain.member.dto;

import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.domain.member.entity.type.AuthorityType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MemberRequestDto {

    public record MemberSignupRequestDto(
            @Email(message = "이메일 형식이 아닙니다.")
            String email,

            @NotBlank(message = "비밀번호를 입력해주세요.")
            String password
    ) {
        public Member toEntity() {
            return Member.builder()
                    .email(email)
                    .password(password)
                    .authority(AuthorityType.USER)
                    .build();
        }
    }
}
