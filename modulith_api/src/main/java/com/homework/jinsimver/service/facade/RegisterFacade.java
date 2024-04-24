package com.homework.jinsimver.service.facade;

import com.homework.jinsimver.dto.request.CreateUserDto;
import com.homework.jinsimver.service.user.UserAuthService;
import com.homework.jinsimver.service.user.UserDetailService;
import com.homework.jinsimver.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterFacade {

    private final UserService userService;
    private final UserDetailService userDetailService;
    private final UserAuthService userAuthService;

    public Long register(CreateUserDto createUserDto) {
        Long userId = userService.saveUser(createUserDto.getNickname());
        userDetailService.saveUserDetail(userId, createUserDto.getName(), createUserDto.getEmail(), createUserDto.getPhoneNumber());
        userAuthService.saveUserAuth(userId, createUserDto.getPassword());
        return userId; // TODO : 테스트용으로 리턴값 넣어줌,,, 테스트 떄문에 로직을 바꿀 수도 없고 이게 맞나 싶고...

    }


}
