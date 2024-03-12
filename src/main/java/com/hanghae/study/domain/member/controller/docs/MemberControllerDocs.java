package com.hanghae.study.domain.member.controller.docs;

import com.hanghae.study.domain.member.dto.MemberRequestDto;
import com.hanghae.study.domain.member.dto.MemberResponseDto;
import com.hanghae.study.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "members", description = "회원 관련 API")
public interface MemberControllerDocs {

    @Operation(summary = "회원 가입 기능", description = "회원 가입할 수 있는 API")
    ResponseDto<MemberResponseDto.MemberSignupResponseDto> signup(
            @RequestBody @Valid MemberRequestDto.MemberSignupRequestDto requestDto
    );
}
