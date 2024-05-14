package com.module.api.domain;

import com.module.api.entity.user.UserAuthEntity;
import com.module.api.entity.user.UserDetailEntity;
import com.module.api.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;


@Getter
@AllArgsConstructor
public class UserInformation {

    private final UserEntity user;
    private final UserDetailEntity userDetail;
    private final UserAuthEntity userAuth;

//    @Builder
//    protected UserInformation (UserEntity user, UserDetailEntity userDetail, UserAuthEntity userAuth) {
//        this.user = user;
//        this.userDetail = userDetail;
//        this.userAuth = userAuth;
//    }

}
