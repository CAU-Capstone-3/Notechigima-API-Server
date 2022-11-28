package com.capstone.notechigima.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"status", "success", "message", "result"})
public class BaseResponse<T> {

    private final int status;

    private final boolean success;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public BaseResponse(SuccessCode status) {
        this.success = true;
        this.status = status.getStatus();
        this.message = status.getMessage();
    }

    public BaseResponse(ExceptionCode status) {
        this.success = false;
        this.status = status.getResponseCode();
        this.message = status.getMessage();
    }

    public BaseResponse(SuccessCode responseStatus, T result) {
        this.success = true;
        this.status = responseStatus.getStatus();
        this.message = responseStatus.getMessage();
        this.result = result;
    }

}
