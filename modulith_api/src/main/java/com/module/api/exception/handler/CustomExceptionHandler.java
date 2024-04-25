package com.module.api.exception.handler;

import com.module.api.exception.CustomException;
import com.module.api.exception.ExceptionReasonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionReasonResponse> handleCustomException (CustomException reasonDto) {
        return ExceptionReasonResponse.toResponseEntity(reasonDto.getCustomErrorCode());
    }
}
