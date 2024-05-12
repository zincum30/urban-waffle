package com.module.api.dto.response;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class LoginResponse {

    String accessToken;
}
