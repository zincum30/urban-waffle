package com.module.api.service.facade;

import com.module.api.dto.request.user.CreateUserDto;
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



    public void register(CreateUserDto createUserDto) {
        Long userId = userService.saveUserNickname(createUserDto);
        userDetailService.saveUserDetail(userId, createUserDto.getName(), createUserDto.getEmail(), createUserDto.getPhoneNumber());
        userAuthService.saveUserAuth(userId, createUserDto.getPassword());
    }


}
