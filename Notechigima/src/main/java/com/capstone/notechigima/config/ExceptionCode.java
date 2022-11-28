package com.capstone.notechigima.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
public enum ExceptionCode {

    ERROR_UNKNOWN(BAD_REQUEST, "알려지지 않은 오류입니다."),
    ERROR_INVALID_ANALYZED_STATUS( BAD_REQUEST, "분석하지 않은 토픽만 요청할 수 있습니다."),
    WRONG_TYPE_TOKEN(BAD_REQUEST, "올바르지 않은 토큰 형식입니다."),
    ERROR_INVALID_REQUEST( BAD_REQUEST, "요청한 값이 잘못되었습니다."),

    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 토큰입니다."),
    ERROR_INVALID_PASSWORD( UNAUTHORIZED, "ID나 PW가 잘못되었습니다."),
    WRONG_TOKEN(UNAUTHORIZED, "올바르지 않은 토큰입니다."),

    PERMISSION_DENIED( FORBIDDEN, "권한이 없습니다."),

    ERROR_NOT_FOUND_RESOURCE(NOT_FOUND, "해당하는 자원이 없습니다."),
    ERROR_NOT_FOUND_USER( NOT_FOUND, "해당하는 사용자 ID가 없습니다"),

    ERROR_DUPLICATED_EMAIL( CONFLICT, "이미 사용 중인 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ExceptionCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
