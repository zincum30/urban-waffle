package com.module.api.dto.request.user;

import lombok.Getter;

@Getter
public class FindUserPasswordRequest {
    private String name;
    private String email;
}
