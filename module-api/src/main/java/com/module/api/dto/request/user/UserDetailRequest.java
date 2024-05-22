package com.module.api.dto.request.user;


import com.module.api.entity.user.UserDetailEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDetailRequest {

    private final UserDetailEntity userDetail;

    @Builder
    public UserDetailRequest(UserDetailEntity userDetail) {
        this.userDetail = userDetail;
    }
}
