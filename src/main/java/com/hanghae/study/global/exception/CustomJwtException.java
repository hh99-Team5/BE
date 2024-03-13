package com.hanghae.study.global.exception;

import lombok.Getter;

@Getter
public class CustomJwtException extends RuntimeException {

    public CustomJwtException(String message) {
        super(message);
    }
}
