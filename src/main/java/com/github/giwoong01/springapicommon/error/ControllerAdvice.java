package com.github.giwoong01.springapicommon.error;

import com.github.giwoong01.springapicommon.error.exception.AccessDeniedGroupException;
import com.github.giwoong01.springapicommon.error.exception.AuthGroupException;
import com.github.giwoong01.springapicommon.error.exception.ConflictGroupException;
import com.github.giwoong01.springapicommon.error.exception.InvalidGroupException;
import com.github.giwoong01.springapicommon.error.exception.NotFoundGroupException;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidGroupException.class)
    public ProblemDetail handleInvalidData(RuntimeException e) {
        return handleException(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(AuthGroupException.class)
    public ProblemDetail handleAuthData(RuntimeException e) {
        return handleException(e, HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(NotFoundGroupException.class)
    public ProblemDetail handleNotFoundData(RuntimeException e) {
        return handleException(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedGroupException.class)
    public ProblemDetail handleAccessDeniedData(RuntimeException e) {
        return handleException(e, HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(ConflictGroupException.class)
    public ProblemDetail handleConflictData(RuntimeException e) {
        return handleException(e, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ProblemDetail handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return handleException(e, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다.");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return handleException(e, HttpStatus.BAD_REQUEST, "요청 본문이 올바르지 않습니다.");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ProblemDetail handleMissingParam(MissingServletRequestParameterException e) {
        return handleException(e, HttpStatus.BAD_REQUEST, "필수 요청 파라미터가 누락되었습니다: " + e.getParameterName());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        return handleException(e, HttpStatus.BAD_REQUEST,
                String.format("파라미터 '%s'의 값 '%s'를 '%s' 타입으로 변환할 수 없습니다.",
                        e.getName(), e.getValue(), Objects.requireNonNull(e.getRequiredType()).getSimpleName()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ProblemDetail handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return handleException(e, HttpStatus.BAD_REQUEST,
                "유효성 검사 실패",
                String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    @ExceptionHandler(Exception.class)
    protected ProblemDetail handleUnknownException(Exception e) {
        String message = (e.getMessage() != null && !e.getMessage().isBlank())
                ? e.getMessage()
                : "서버 내부 오류가 발생했습니다.";
        return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    private ProblemDetail handleException(Exception e, HttpStatus status, String title, String... info) {
        log.error("Exception handled: {}", e.getMessage());
        return createProblemDetail(e, status, title, info);
    }

    private ProblemDetail createProblemDetail(final Exception e,
                                              final HttpStatus status,
                                              final String title,
                                              final String... info) {
        ErrorResponse.Builder builder = ErrorResponse.builder(e, status, title)
                .type(URI.create("errors"))
                .property("timestamp", OffsetDateTime.now().toString());

        if (info.length > 0) {
            builder.property("info", info[0]);
        }

        return builder.build().getBody();
    }

}
