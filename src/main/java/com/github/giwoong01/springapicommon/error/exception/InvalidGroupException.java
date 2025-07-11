package com.github.giwoong01.springapicommon.error.exception;

import lombok.Getter;

/**
 * 요청 데이터가 유효하지 않거나 잘못된 형식일 때 발생하는 예외입니다.
 * HTTP 상태 코드: 400 BAD_REQUEST
 * 예: 필수 필드 누락, 잘못된 데이터 형식.
 */
@Getter
public abstract class InvalidGroupException extends RuntimeException {
    private final String errorCode = "INVALID_DATA";

    public InvalidGroupException(String message) {
        super(message);
    }

    public InvalidGroupException() {
        super("요청 데이터가 유효하지 않습니다.");
    }
}