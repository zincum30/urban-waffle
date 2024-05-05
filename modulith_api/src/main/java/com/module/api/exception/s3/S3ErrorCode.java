package com.module.api.exception.s3;

import com.module.api.exception.custom.CustomErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum S3ErrorCode {

    // TODO :

//    INVALID_FILE_EXTENTION(HttpStatus.INTERNAL_SERVER_ERROR, "올바르지 않은 확장자입니다.");

    INVALID_FILE_EXTENTION(new CustomErrorCode(HttpStatus.INTERNAL_SERVER_ERROR, "올바르지 않은 확장자입니다."));


//    private final HttpStatus status;
//    private final String reasonPhrase;

    private final CustomErrorCode errorCode;

}
