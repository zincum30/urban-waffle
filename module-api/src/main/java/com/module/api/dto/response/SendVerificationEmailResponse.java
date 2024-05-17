package com.module.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "이메일 주소 확인")
@Getter
public class SendVerificationEmailResponse {

    @Schema(description = "이메일 주소")
    private String email;

    @Schema(description = "인증 번호")
    private String certificationNumber;

}
