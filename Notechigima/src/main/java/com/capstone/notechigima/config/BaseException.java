package com.capstone.notechigima.config;

import lombok.Getter;

@Getter
public class BaseException extends Exception {

    private final BaseResponseStatus exceptionStatus;

    public BaseException(final BaseResponseStatus exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }
}
