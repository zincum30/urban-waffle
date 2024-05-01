package com.module.api.service.facade;

import com.module.api.authentication.jwt.JwtProvider;
import com.module.api.dto.request.LoginDto;
import com.module.api.dto.response.ActivateDormantAccountResponse;
import com.module.api.dto.response.LoginResponse;
import com.module.api.exception.CustomErrorCode;
import com.module.api.exception.CustomException;
import com.module.api.service.SendEmailService;
import com.module.api.service.user.UserAuthService;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class LoginFacade {
    private final UserService userService;
    private final UserDetailService userDetailService;
    private final UserAuthService userAuthService;
    private final SendEmailService sendEmailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    // TO DO : 이벤트 처리 알아보기

    public LoginResponse signIn(LoginDto loginDto) {

        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        Long userId = userDetailService.findUserIdByEmail(email);
        String encodedPassword = userAuthService.findPassword(userId);

        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }

        if (!(userService.isDormant(userId) && userDetailService.existsByUserId(userId))) {
            sendEmailService.sendEmailForCertification(email);
            }
            userService.updateLastLoginDate(userId);
            return LoginResponse.builder()
                    .accessToken(jwtProvider.createAccessToken(userId)).build();

    }
}
