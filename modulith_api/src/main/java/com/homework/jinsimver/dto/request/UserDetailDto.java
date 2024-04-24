package com.homework.jinsimver.dto.request;


import com.homework.jinsimver.entity.user.UserDetailEntity;
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
