package com.capstone.notechigima.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    SUCCESS_READ(true, 200, "요청에 성공하였습니다."),
    EMAIL_AVAILABLE(true, 200, "사용 가능한 이메일입니다"),
    SUCCESS_WRITE(true, 201, "요청 처리를 완료하였습니다."),


    ERROR_INVALID_REQUEST(false, 404, "요청한 값이 잘못되었습니다."),
    ERROR_AUTH(false, 401, "접근할 수 없는 경로입니다."),
    ERROR_NOT_FOUND_USER(false, 401, "해당하는 사용자 ID가 없습니다"),
    ERROR_INVALID_PASSWORD(false, 401, "ID나 PW가 잘못되었습니다."),
    ERROR_INVALID_ANALYZED_STATUS(false, 404, "분석하지 않은 토픽만 요청할 수 있습니다."),
    ERROR_DUPLICATED_EMAIL(false, 409, "이미 사용 중인 이메일입니다.");

    private final boolean success;
    private final int status;
    private final String message;
}
