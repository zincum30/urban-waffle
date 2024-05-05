package com.module.api.exception.custom;

import com.module.api.exception.api.ApiErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class CustomExceptionResponse {

    private final String error;
    private final String phrase;

    public static ResponseEntity<CustomExceptionResponse> toResponseEntity(CustomErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(CustomExceptionResponse.builder()
                        .error(errorCode.getStatus().name())
                        .phrase(errorCode.getReasonPhrase())
                        .build()
                );
        }
}
