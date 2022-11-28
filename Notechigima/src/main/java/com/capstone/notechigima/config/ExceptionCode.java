package com.capstone.notechigima.config;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    ERROR_INVALID_REQUEST( 404, "요청한 값이 잘못되었습니다."),
    ERROR_AUTH( 401, "접근할 수 없는 경로입니다."),
    ERROR_NOT_FOUND_USER( 401, "해당하는 사용자 ID가 없습니다"),
    ERROR_INVALID_PASSWORD( 401, "ID나 PW가 잘못되었습니다."),
    ERROR_INVALID_ANALYZED_STATUS( 404, "분석하지 않은 토픽만 요청할 수 있습니다."),
    ERROR_DUPLICATED_EMAIL( 409, "이미 사용 중인 이메일입니다.");

    private final int code;
    private final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
