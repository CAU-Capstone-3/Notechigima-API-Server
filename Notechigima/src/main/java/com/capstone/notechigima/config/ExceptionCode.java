package com.capstone.notechigima.config;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    // 인증 및 인가
    ERROR_UNKNOWN(4000, "알려지지 않은 오류입니다."),
    ERROR_INVALID_ANALYZED_STATUS( 4001, "분석하지 않은 토픽만 요청할 수 있습니다."),

    WRONG_TYPE_TOKEN(4011, "올바르지 않은 토큰 형식입니다."),
    EXPIRED_TOKEN(4012, "만료된 토큰입니다."),
    ERROR_INVALID_PASSWORD( 4013, "ID나 PW가 잘못되었습니다."),
    ERROR_NOT_FOUND_USER( 4014, "해당하는 사용자 ID가 없습니다"),
    WRONG_TOKEN(4015, "올바르지 않은 토큰입니다."),

    ERROR_AUTH_DENIED( 4030, "권한이 없습니다."),

    ERROR_INVALID_REQUEST( 4040, "요청한 값이 잘못되었습니다."),
    ERROR_DUPLICATED_EMAIL( 4091, "이미 사용 중인 이메일입니다.");

    private final int code;
    private final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getResponseCode() {
        return (this.code / 10);
    }
}
