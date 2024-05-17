package com.module.api.dto.request.user;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String email;
    private String password;
}
