package com.module.api.dto.request;


import com.module.api.entity.user.UserDetailEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDetailDto {

    private final UserDetailEntity userDetail;

    @Builder
    public UserDetailDto(UserDetailEntity userDetail) {
        this.userDetail = userDetail;
    }
}
