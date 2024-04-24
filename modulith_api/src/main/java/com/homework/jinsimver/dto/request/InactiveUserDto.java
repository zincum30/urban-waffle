package com.homework.jinsimver.dto.request;

import com.homework.jinsimver.entity.user.UserDetailEntity;
import com.homework.jinsimver.entity.user.UserEntity;
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
