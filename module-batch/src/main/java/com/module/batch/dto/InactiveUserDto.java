package com.module.batch.dto;

import com.module.batch.entity.UserDetailEntity;
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
