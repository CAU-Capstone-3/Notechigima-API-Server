package com.capstone.notechigima.config;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;

    @Builder
    public ErrorResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ExceptionCode exceptionCode) {
        return ResponseEntity
                .status(exceptionCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(exceptionCode.getHttpStatus().value())
                        .code(exceptionCode.name())
                        .message(exceptionCode.getMessage())
                        .build());
    }
}
