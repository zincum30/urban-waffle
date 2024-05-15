package com.module.api.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LoginDto {

    private String email;
    private String password;
}
