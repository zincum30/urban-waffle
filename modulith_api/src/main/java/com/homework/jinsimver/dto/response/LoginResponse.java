package com.homework.jinsimver.dto.response;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class LoginResponse {

    String accessToken;
}
