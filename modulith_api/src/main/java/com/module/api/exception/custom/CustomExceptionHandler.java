package com.module.api.exception.custom;

import com.module.api.exception.api.ApiException;
import com.module.api.exception.custom.CustomException;
import com.module.api.exception.custom.CustomExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handleCustomException (CustomException reasonDto) {
        return CustomExceptionResponse.toResponseEntity(reasonDto.getCustomErrorCode());
    }
}
