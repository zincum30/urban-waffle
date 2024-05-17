package com.module.api.exception.api;

import com.module.api.exception.custom.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ApiException extends CustomException {

    private ApiErrorCode apiErrorCode;

    public ApiException(ApiErrorCode apiErrorCode) {}

}


