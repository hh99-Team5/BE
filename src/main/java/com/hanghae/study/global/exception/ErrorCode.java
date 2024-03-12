package com.hanghae.study.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다."),
    NOT_FOUND_EMAIL("존재하지 않는 이메일입니다."),
    NOT_FOUND_MEMBER("찾을 수 없는 회원입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
