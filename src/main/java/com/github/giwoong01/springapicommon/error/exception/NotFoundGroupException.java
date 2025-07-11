package com.github.giwoong01.springapicommon.error.exception;

import lombok.Getter;

/**
 * 요청한 리소스를 찾을 수 없을 때 발생하는 예외입니다.
 * HTTP 상태 코드: 404 NOT_FOUND
 * 예: 존재하지 않는 ID로 리소스를 조회하려고 할 때.
 */
@Getter
public abstract class NotFoundGroupException extends RuntimeException {
    private final String errorCode = "NOT_FOUND";

    public NotFoundGroupException(String message) {
        super(message);
    }

    public NotFoundGroupException() {
        super("요청한 리소스를 찾을 수 없습니다.");
    }
}