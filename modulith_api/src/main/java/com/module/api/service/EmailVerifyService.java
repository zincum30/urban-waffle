package com.module.api.service;

import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import com.module.api.certification.CertificationRedisTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerifyService {

    private final CertificationRedisTemplate certificationRedisTemplate;

    public void verifyEmail(String email, String certificationNumber) {
        if (!isVerify(email, certificationNumber)) {
            throw new ApiException(ApiErrorCode.INVALID_CERTIFICATION_NUMBER);
        }
        certificationRedisTemplate.removeCertificationNumber(email);
    }


    private boolean isVerify(String email, String certificationNumber) {
        if (!certificationRedisTemplate.hasKey(email)) {
            throw new ApiException(ApiErrorCode.EMAIL_NOT_FOUND);
        }
        return certificationRedisTemplate.getCertificationNumber(email).equals(certificationNumber);
    }
}




