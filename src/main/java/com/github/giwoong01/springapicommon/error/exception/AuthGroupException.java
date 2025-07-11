package com.github.giwoong01.springapicommon.error.exception;

import lombok.Getter;

/**
 * 인증 또는 권한 관련 문제가 발생했을 때 사용되는 예외입니다.
 * HTTP 상태 코드: 401 UNAUTHORIZED
 * 예: 잘못된 인증 토큰 또는 만료된 세션.
 */
@Getter
public abstract class AuthGroupException extends RuntimeException {
    private final String errorCode = "AUTH_ERROR";

    public AuthGroupException(String message) {
        super(message);
    }

    public AuthGroupException() {
        super("인증 또는 권한 문제가 발생했습니다.");
    }
}