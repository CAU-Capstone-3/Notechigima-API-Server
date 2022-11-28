package com.capstone.notechigima.config;

import lombok.Getter;

@Getter
public class BaseException extends Exception {

    private final ExceptionCode exceptionStatus;

    public BaseException(final ExceptionCode exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }
}
