package com.module.api.dto.request.user;

import lombok.Getter;

@Getter
public class CreateUserRequest {

    private Long userId;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String password;


}
