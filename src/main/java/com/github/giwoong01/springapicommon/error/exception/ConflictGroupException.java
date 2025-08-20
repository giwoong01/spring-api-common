package com.github.giwoong01.springapicommon.error.exception;

import lombok.Getter;

/**
 * 리소스 충돌(Conflict) 상황에서 발생하는 예외입니다.
 * HTTP 상태 코드: 409 CONFLICT
 */
@Getter
public class ConflictGroupException extends RuntimeException {
    private final String errorCode = "CONFLICT";

    public ConflictGroupException(String message) {
        super(message);
    }

    public ConflictGroupException() {
        super("");
    }
}
