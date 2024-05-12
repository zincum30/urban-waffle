package com.module.api.exception.custom;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomErrorCode {

    private final HttpStatus status;
    private final String reasonPhrase;

}
