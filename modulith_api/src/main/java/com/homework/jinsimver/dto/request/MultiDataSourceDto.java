package com.homework.jinsimver.dto.request;

import com.homework.jinsimver.dormant.entity.InactiveUserDetailEntity;
import com.homework.jinsimver.entity.user.UserDetailEntity;
import com.homework.jinsimver.entity.user.UserEntity;
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
