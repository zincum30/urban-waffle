package com.module.api.service.facade;

import com.module.api.authentication.jwt.JwtProvider;
import com.module.api.dto.request.user.LoginRequest;
import com.module.api.dto.response.LoginResponse;
import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import com.module.api.service.SendEmailService;
import com.module.api.service.user.UserAuthService;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginFacade {
    private final UserService userService;
    private final UserDetailService userDetailService;
    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    // TO DO : 이벤트 처리 알아보기

    public LoginResponse signIn(LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Long userId = userDetailService.findUserIdByEmail(email);
        String encodedPassword = userAuthService.findPassword(userId);

        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new ApiException(ApiErrorCode.USER_NOT_FOUND);
        }

//        if (!(userService.isDormant(userId) && userDetailService.existsByUserId(userId))) {
//            sendEmailService.sendMail(email);
//            }
//            userService.updateLastLoginDate(userId);
//            return LoginResponse.builder()
//                    .accessToken(jwtProvider.createAccessToken(userId)).build();

        if (!(userService.isDormant(userId) && userDetailService.existsByUserId(userId))) {
            userService.updateLastLoginDate(userId);
        } else throw new ApiException(ApiErrorCode.USER_NOT_FOUND);

        return LoginResponse.builder()
                .accessToken(jwtProvider.createAccessToken(userId)).build();
    }
}

