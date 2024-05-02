package com.module.api.service.facade;

import com.module.api.service.user.UserAuthService;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserAccountFacade {

    private final UserService userService;
    private final UserDetailService userDetailService;
    private final UserAuthService userAuthService;

    public void withdrawUserAccount(Long userId) {
        userAuthService.withdrawUserAuth(userId);
        userDetailService.withdrawUserDetail(userId);
        userService.withdrawUser(userId);
    }
}
