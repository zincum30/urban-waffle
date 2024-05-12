package com.module.batch.dto;


import com.module.batch.entity.InactiveUserDetailEntity;
import com.module.batch.entity.UserDetailEntity;
import lombok.Builder;
import lombok.Getter;

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
