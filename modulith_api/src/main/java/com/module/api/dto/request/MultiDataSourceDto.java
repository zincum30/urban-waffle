package com.module.api.dto.request;

import com.module.api.dormant.entity.InactiveUserDetailEntity;
import com.module.api.entity.user.UserDetailEntity;
import lombok.Builder;
import lombok.Getter;
import org.apache.catalina.User;

@Getter
public class MultiDataSourceDto {

    private final UserDetailEntity main;
    private final InactiveUserDetailEntity dormant;

    @Builder
    public MultiDataSourceDto(UserDetailEntity main, InactiveUserDetailEntity dormant) {
        this.main = main;
        this.dormant = dormant;
    }
}
