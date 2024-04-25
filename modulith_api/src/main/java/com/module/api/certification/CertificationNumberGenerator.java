package com.module.api.certification;

import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class CertificationNumberGenerator {
    public String createCertificationNumber() throws NoSuchAlgorithmException {

        int randomNumber = SecureRandom.getInstanceStrong().nextInt(999999);
        String certificationNumber = String.format("%06d", randomNumber);

        return certificationNumber;
    }
}
