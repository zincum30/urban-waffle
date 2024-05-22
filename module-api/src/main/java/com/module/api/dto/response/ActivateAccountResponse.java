package com.module.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "휴면계정 활성화 인증메일")
@Getter
public class ActivateAccountResponse {

    @Schema(description = "이메일 주소")
    String email;

    @Schema(description = "인증 번호")
    String certificationNumber;
}
