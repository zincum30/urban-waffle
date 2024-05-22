package com.module.api.service.facade;

import com.module.api.dto.request.user.CreateUserRequest;
import com.module.api.service.user.UserAuthService;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterFacade {

    private final UserService userService;
    private final UserDetailService userDetailService;
    private final UserAuthService userAuthService;



    public void register(CreateUserRequest createUserRequest) {
        Long userId = userService.saveUserNickname(createUserRequest);
        userDetailService.saveUserDetail(userId, createUserRequest.getName(), createUserRequest.getEmail(), createUserRequest.getPhoneNumber());
        userAuthService.saveUserAuth(userId, createUserRequest.getPassword());
    }


}
