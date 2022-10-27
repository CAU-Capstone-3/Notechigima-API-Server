package com.capstone.notechigima.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    SUCCESS_READ(true, 200, "요청에 성공하였습니다."),
    SUCCESS_EDIT(true, 201, "요청 처리를 완료하였습니다."),

    REQUEST_ERROR(false, 404, "요청한 값이 잘못되었습니다.");

    private final boolean success;
    private final int status;
    private final String message;
}
