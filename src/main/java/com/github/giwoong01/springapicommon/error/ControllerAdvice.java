package com.github.giwoong01.springapicommon.error;

import com.github.giwoong01.springapicommon.error.exception.AccessDeniedGroupException;
import com.github.giwoong01.springapicommon.error.exception.AuthGroupException;
import com.github.giwoong01.springapicommon.error.exception.InvalidGroupException;
import com.github.giwoong01.springapicommon.error.exception.NotFoundGroupException;
import com.github.giwoong01.springapicommon.template.RspTemplate;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidGroupException.class)
    public ResponseEntity<RspTemplate<Void>> handleInvalidData(RuntimeException e) {
        log.error(e.getMessage());
        return RspTemplate.<Void>builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(AuthGroupException.class)
    public ResponseEntity<RspTemplate<Void>> handleAuthData(RuntimeException e) {
        log.error(e.getMessage());
        return RspTemplate.<Void>builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(NotFoundGroupException.class)
    public ResponseEntity<RspTemplate<Void>> handleNotFoundData(RuntimeException e) {
        log.error(e.getMessage());
        return RspTemplate.<Void>builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(AccessDeniedGroupException.class)
    public ResponseEntity<RspTemplate<Void>> handleAccessDeniedData(RuntimeException e) {
        log.error(e.getMessage());
        return RspTemplate.<Void>builder()
                .statusCode(HttpStatus.FORBIDDEN)
                .message(e.getMessage())
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<RspTemplate<String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.warn(e.getMethod());
        return RspTemplate.<String>builder()
                .statusCode(HttpStatus.METHOD_NOT_ALLOWED)
                .message("지원하지 않는 HTTP 메서드입니다.")
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<RspTemplate<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn(e.getMessage());
        return RspTemplate.<String>builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message("요청 본문이 올바르지 않습니다.")
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<RspTemplate<String>> handleMissingParam(MissingServletRequestParameterException e) {
        log.warn("Missing parameter: {}", e.getParameterName());
        return RspTemplate.<String>builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message("필수 요청 파라미터가 누락되었습니다: " + e.getParameterName())
                .build()
                .toResponseEntity();
    }

    // Validation 관련 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<RspTemplate<String>> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        String errorMessage = String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField());

        log.error("Validation error for field {}: {}", fieldError.getField(), fieldError.getDefaultMessage());
        return RspTemplate.<String>builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message("유효성 검사 실패")
                .data(errorMessage)
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<RspTemplate<String>> handleUnknownException(Exception e) {
        log.error("Unhandled exception", e);
        return RspTemplate.<String>builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("서버 내부 오류가 발생했습니다.")
                .build()
                .toResponseEntity();
    }
}
