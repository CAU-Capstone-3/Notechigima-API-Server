package com.capstone.notechigima.config;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"status", "success", "message", "result"})
public class ResponseEntity<T> {

    private final int status;

    private final boolean success;

    private final String message;

    private T result;

    public ResponseEntity(BaseResponseStatus responseStatus, T result) {
        this.status = responseStatus.getStatus();
        this.success = responseStatus.isSuccess();
        this.message = responseStatus.getMessage();
        this.result = result;
    }

}
