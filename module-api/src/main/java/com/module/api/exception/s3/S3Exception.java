package com.module.api.exception.s3;

import com.module.api.exception.custom.CustomException;

public class S3Exception extends CustomException {

    private S3ErrorCode s3ErrorCode;

    public S3Exception(S3ErrorCode s3ErrorCode) {super();}
}
