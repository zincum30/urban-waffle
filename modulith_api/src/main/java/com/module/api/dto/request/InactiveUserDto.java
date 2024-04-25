package com.module.api.dto.request;

import com.module.api.entity.user.UserDetailEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InactiveUserDto {

    private final UserDetailEntity inactiveUser;

    @Builder
    public InactiveUserDto (UserDetailEntity inactiveUser) {

        this.inactiveUser = inactiveUser;
    }

}
