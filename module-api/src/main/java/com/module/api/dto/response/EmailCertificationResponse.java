package com.module.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class EmailCertificationResponse {

    private String email;
    private String certificationNumber;

}