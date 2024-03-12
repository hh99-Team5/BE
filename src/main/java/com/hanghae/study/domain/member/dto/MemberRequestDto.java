package com.hanghae.study.domain.member.dto;

import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.domain.member.entity.type.AuthorityType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MemberRequestDto {

    public record SignupMemberRequestDto(
            @Schema(description = "이메일", example = "test@test.com")
            @NotBlank(message = "이메일을 입력해주세요.")
            @Email(message = "이메일 형식이 아닙니다.")
            String email,

            @Schema(description = "비밀번호", example = "password123!")
            @NotBlank(message = "비밀번호를 입력해주세요.")
            String password
    ) {
        public Member toEntity(String encodedPassword) {
            return Member.builder()
                    .email(email)
                    .password(encodedPassword)
                    .authority(AuthorityType.USER)
                    .build();
        }
    }

    public record SigninMemberRequestDto(
            @Schema(description = "이메일", example = "test@test.com")
            @NotBlank(message = "이메일을 입력해주세요.")
            @Email(message = "이메일 형식이 아닙니다.")
            String username,

            @Schema(description = "비밀번호", example = "password123!")
            @NotBlank(message = "비밀번호를 입력해주세요.")
            String password
    ) {

    }

    public record EditMemberRequestDto(
            @Schema(description = "비밀번호", example = "password123!")
            @NotBlank(message = "변경할 비밀번호를 입력해주세요.")
            String password
    ) {

    }
}
