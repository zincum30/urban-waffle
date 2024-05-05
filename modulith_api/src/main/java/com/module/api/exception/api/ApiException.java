package com.module.api.exception.api;

import com.module.api.exception.custom.CustomException;
import lombok.Getter;

@Getter
public class ApiException extends CustomException {

    private ApiErrorCode apiErrorCode;

    public ApiException(ApiErrorCode apiErrorCode) {
        super();
    }
}


