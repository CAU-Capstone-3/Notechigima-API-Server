package com.capstone.notechigima.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.AccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException() {
        log.error("handleNoSuchElementException throw Exception : {}", ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        return ErrorResponse.toResponseEntity(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleNotFoundException() {
        log.error("handleNotFoundException throw Exception : {}", ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        return ErrorResponse.toResponseEntity(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
    }

    @ExceptionHandler(value = {AccessException.class})
    protected ResponseEntity<ErrorResponse> handleAccessException() {
        log.error("handleAccessException throw Exception : {}", ExceptionCode.PERMISSION_DENIED);
        return ErrorResponse.toResponseEntity(ExceptionCode.PERMISSION_DENIED);
    }

}
