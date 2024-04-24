package com.homework.jinsimver.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CreateUserDto {

    private Long userId;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String password;


}
