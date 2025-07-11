package com.github.giwoong01.springapicommon.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RspTemplate<T> {
    private final int statusCode;
    private final String message;
    private final T data;

    private RspTemplate(Builder<T> builder) {
        this.statusCode = builder.statusCode;
        this.message = builder.message;
        this.data = builder.data;
    }

    public ResponseEntity<RspTemplate<T>> toResponseEntity() {
        return ResponseEntity.status(this.statusCode).body(this);
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private int statusCode;
        private String message;
        private T data;

        public Builder<T> statusCode(HttpStatus httpStatus) {
            this.statusCode = httpStatus.value();
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public RspTemplate<T> build() {
            return new RspTemplate<>(this);
        }
    }
}