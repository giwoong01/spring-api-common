package com.github.giwoong01.springapicommon.error.dto;

public record ErrorResponse(
        int statusCode,
        String message
) {
}