package com.module.api.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ExceptionReasonResponse {

    private final String error;
    private final String message;

    public static ResponseEntity<ExceptionReasonResponse> toResponseEntity(CustomErrorCode customErrorCode) {
        return ResponseEntity
                .status(customErrorCode.getHttpStatus())
                .body(ExceptionReasonResponse.builder()
                        .error(customErrorCode.getHttpStatus().name())
                        .message(customErrorCode.getDetail())
                        .build()
                );
        }
}
