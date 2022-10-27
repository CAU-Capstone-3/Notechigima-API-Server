package com.capstone.notechigima.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"status", "success", "message", "result"})
public class BaseResponse<T> {

    private final int status;

    private final boolean success;

    private final String message;

    private T result;

    public BaseResponse(BaseResponseStatus responseStatus, T result) {
        this.status = responseStatus.getStatus();
        this.success = responseStatus.isSuccess();
        this.message = responseStatus.getMessage();
        this.result = result;
    }

}
