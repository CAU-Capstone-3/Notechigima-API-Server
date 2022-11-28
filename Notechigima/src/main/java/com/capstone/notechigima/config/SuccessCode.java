package com.capstone.notechigima.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    SUCCESS_READ( 200, "요청에 성공하였습니다."),
    EMAIL_AVAILABLE( 200, "사용 가능한 이메일입니다"),
    SUCCESS_WRITE( 201, "요청 처리를 완료하였습니다.");

    private final int status;
    private final String message;
}
