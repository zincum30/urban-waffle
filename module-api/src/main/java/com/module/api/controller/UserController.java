package com.module.api.controller;

import com.module.api.annotation.JwtRequired;
import com.module.api.dto.request.user.CheckCertificationRequest;
import com.module.api.dto.request.user.CreateUserDto;
import com.module.api.dto.request.user.FindUserPasswordDto;
import com.module.api.dto.request.user.LoginDto;
import com.module.api.dto.request.user.ProfileImageRequest;
import com.module.api.dto.request.user.UpdateNicknameDto;
import com.module.api.dto.request.user.UpdatePassworDto;
import com.module.api.dto.response.LoginResponse;
import com.module.api.dto.response.UserProfileResponse;
import com.module.api.service.EmailVerifyService;
import com.module.api.service.SendEmailService;
import com.module.api.service.facade.DeleteUserAccountFacade;
import com.module.api.service.facade.FindUserPasswordFacade;
import com.module.api.service.facade.FindUserProfileFacade;
import com.module.api.service.facade.LoginFacade;
import com.module.api.service.facade.RegisterFacade;
import com.module.api.service.user.UserAuthService;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final RegisterFacade registerFacade;
    private final LoginFacade loginFacade;
    private final UserDetailService userDetailService;
    private final UserService userService;
    private final UserAuthService userAuthService;
    private final DeleteUserAccountFacade deleteUserAccountFacade;
    private final FindUserProfileFacade findUserProfileFacade;
    private final FindUserPasswordFacade findUserPasswordFacade;
    private final SendEmailService sendEmailService;
    private final EmailVerifyService verifyService;

    @PostMapping("/join")
    public void register(@RequestBody CreateUserDto createUserDto) {
        registerFacade.register(createUserDto);
    }

    @GetMapping("/join")
    public void checkDuplicatedEmail(@RequestParam("email") String email) {
        userDetailService.checkDuplicatedEmail(email);
    }

    @GetMapping("/{user-id}")
    public void checkDuplicatedNickname(@PathVariable("user-id") String userId, @RequestParam("nickname") String nickname) {
        userService.checkDuplicatedNickname(nickname);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto loginDto) {

        // response로 토큰 돌려줌
        return loginFacade.signIn(loginDto);
    }

    @PostMapping("/help/password")
    public void findPassword(@RequestBody FindUserPasswordDto findUserPasswordDto) {
        findUserPasswordFacade.FindUserPassword(findUserPasswordDto);
    }

    @GetMapping("/help/certification")
    public void sendEmail(@RequestParam("email") String email) {
        sendEmailService.sendMail(email);
    }

    @PostMapping("/help/certification")
    public void confirmCertificatedUser(@RequestBody CheckCertificationRequest checkCertificationRequest) {
        String email = checkCertificationRequest.getMail();
        String number = checkCertificationRequest.getCertificationNumber();
        verifyService.verifyEmail(email, number);
    }


    @GetMapping("/{nickname}")
    public UserProfileResponse userProfileResponse(@RequestParam("nickname") String nickname) {
        return findUserProfileFacade.userProfileResponse(nickname);
    }

    @PutMapping("/{nickname}")
    public void updateProfileImg(
            @RequestBody ProfileImageRequest profileImageRequest, Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateUserImg(profileImageRequest, userId);
    }

    @PutMapping("/{nickname}")
    public void updateNickname(@RequestBody UpdateNicknameDto nickname, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateNickname(nickname, userId);
    }

    @JwtRequired
    @PostMapping("/{nickname}/security")
    public void updatePassword(UpdatePassworDto passworDto) {
        userAuthService.updatePassword(passworDto);
    }


    @DeleteMapping("/{nickname}")
    public void withdrawUserAccount(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        deleteUserAccountFacade.withdrawUserAccount(userId);
    }

}
