package com.github.giwoong01.springapicommon.error.exception;

import lombok.Getter;

/**
 * 사용자가 접근 권한이 없는 리소스에 접근하려고 할 때 발생하는 예외입니다.
 * HTTP 상태 코드: 403 FORBIDDEN
 * 예: 인증되지 않은 사용자 또는 권한이 부족한 사용자가 리소스에 접근 시.
 */
@Getter
public abstract class AccessDeniedGroupException extends RuntimeException {
    private final String errorCode = "ACCESS_DENIED";

    public AccessDeniedGroupException(String message) {
        super(message);
    }

    public AccessDeniedGroupException() {
        super("접근 권한이 없습니다.");
    }
}
