package com.module.api.dto.request.user;


import lombok.Getter;

@Getter
public class CheckCertificationNumberRequest {

    private String mail;
    private String certificationNumber;
}
