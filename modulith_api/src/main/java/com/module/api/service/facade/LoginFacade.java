package com.module.api.service.facade;

import com.module.api.authentication.jwt.JwtProvider;
import com.module.api.dto.request.LoginDto;
import com.module.api.dto.response.LoginResponse;
import com.module.api.exception.CustomErrorCode;
import com.module.api.exception.CustomException;
import com.module.api.service.user.UserAuthService;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginFacade {
    private final UserService userService;
    private final UserDetailService userDetailService;
    private final UserAuthService userAuthService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResponse signIn(LoginDto loginDto) {

        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        Long userId = userDetailService.findUserIdByEmail(email);
        String encodedPassword = userAuthService.findPassword(userId);

        // TODO : 예외처리를 할지, Controller에서 response 값으로 돌려주는 게 나을지...?
        // DO : 이미 CustomException을 RestControllerAdvice에서 처리하고 있음!
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }

        userService.updateLastLoginDate(userId);
        return LoginResponse.builder()
                .accessToken(jwtProvider.createAccessToken(userId))
                .build();
    }
}
