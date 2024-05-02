package com.module.api.controller;

import com.module.api.dto.request.CreateUserDto;
import com.module.api.dto.request.LoginDto;
import com.module.api.dto.request.ProfileImageRequest;
import com.module.api.dto.request.UpdateNicknameDto;
import com.module.api.dto.request.UpdatePassworDto;
import com.module.api.dto.response.LoginResponse;
import com.module.api.service.facade.FindUserProfileFacade;
import com.module.api.service.facade.LoginFacade;
import com.module.api.service.facade.RegisterFacade;
import com.module.api.service.facade.DeleteUserAccountFacade;
import com.module.api.service.user.UserAuthService;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final RegisterFacade registerFacade;
    private final LoginFacade loginFacade;
    private final UserDetailService userDetailService;
    private final UserService userService;
    private final UserAuthService userAuthService;
    private final DeleteUserAccountFacade deleteUserAccountFacade;
    private final FindUserProfileFacade findUserProfileFacade;


    @PostMapping
    public void register(@RequestBody CreateUserDto createUserDto) {
        registerFacade.register(createUserDto);
    }

    @GetMapping("/mail")
    public void checkDuplicatedEmail(@RequestParam("mail") String email) {
        userDetailService.checkDuplicatedEmail(email);
    }

    @GetMapping("/nickname")
    public void checkDuplicatedNickname(@RequestParam("nickname") String nickname) {
        userService.checkDuplicatedNickname(nickname);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto loginDto) {

        // response로 토큰 돌려줌
        return loginFacade.signIn(loginDto);
    }


    @PutMapping("/me/img")
    public void updateProfileImg(
            @RequestBody ProfileImageRequest profileImageRequest, Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateUserImg(profileImageRequest, userId);
    }

    @PutMapping("/me/nickname")
    public void updateNickname(UpdateNicknameDto nickname, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateNickname(nickname, userId);
    }

    @PostMapping("/me/password")
    public void updatePassword(UpdatePassworDto passworDto, Authentication authentication) {
        userAuthService.updatePassword(passworDto);
    }


    @GetMapping("/me")
    public void findUserProfile(@RequestParam("nickname") String nickname) {

        findUserProfileFacade.userProfileResponse(nickname);

    }


    @DeleteMapping("/withdraw")
    public void withdrawUserAccount(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        deleteUserAccountFacade.withdrawUserAccount(userId);
    }

}
