package com.module.api.controller;

import com.module.api.dto.request.user.CheckCertificationNumberRequest;
import com.module.api.dto.request.user.CreateUserRequest;
import com.module.api.dto.request.user.FindUserPasswordRequest;
import com.module.api.dto.request.user.LoginRequest;
import com.module.api.dto.request.user.ProfileImageRequest;
import com.module.api.dto.request.user.UpdateNicknameRequest;
import com.module.api.dto.request.user.UpdatePassworRequest;
import com.module.api.dto.response.LoginResponse;
import com.module.api.dto.response.UserProfileResponse;
import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "회원 API")
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

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public void register(@RequestBody CreateUserRequest createUserRequest) {
        registerFacade.register(createUserRequest);
    }

    @Operation(summary = "이메일 중복 확인")
    @GetMapping("/join")
    public void checkDuplicatedEmail(@RequestParam("email") String email) {
        userDetailService.checkDuplicatedEmail(email);
        throw new ApiException(ApiErrorCode.CONFLICT_NICKNAME);
    }

    @Operation(summary = "닉네임 중복 확인")
    @GetMapping
    public ResponseEntity<String> checkDuplicatedNickname(@RequestParam(name = "nickname") String nickname) {
        userService.checkDuplicatedNickname(nickname);
        return ResponseEntity.ok("사용가능한 닉네임입니다.");
    }


    @Operation(summary = "회원 로그인")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        // response로 토큰 돌려줌
        return loginFacade.signIn(loginRequest);
    }

    @Operation(summary = "비밀번호 찾기")
    @PostMapping("/help/password")
    public void findPassword(@RequestBody FindUserPasswordRequest findUserPasswordRequest) {
        findUserPasswordFacade.FindUserPassword(findUserPasswordRequest);
    }

    @Operation(summary = "인증메일 발송")
    @GetMapping("/help/certification")
    public void sendEmail(@RequestParam("email") String email) {
        sendEmailService.sendMail(email);
    }


    @Operation(summary = "인증번호 확인")
    @PostMapping("/help/certification")
    public void confirmCertificatedUser(@RequestBody CheckCertificationNumberRequest checkCertificationNumberRequest) {
        String email = checkCertificationNumberRequest.getMail();
        String number = checkCertificationNumberRequest.getCertificationNumber();
        verifyService.verifyEmail(email, number);
    }


    @Operation(summary = "회원 프로필 정보 조회")
    @GetMapping("/{nickname}")
    public UserProfileResponse userProfileResponse(@PathVariable(value = "nickname") String nickname) {
        return findUserProfileFacade.userProfileResponse(nickname);
    }

    @Operation(summary = "회원 프로필 이미지 수정")
    @PutMapping("/{nickname}/image")
    public void updateProfileImg(
            @RequestBody ProfileImageRequest profileImageRequest, Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateUserImg(profileImageRequest, userId);
    }

    @Operation(summary = "회원 닉네임 수정")
    @PutMapping("/{nickname}")
    public void updateNickname(@RequestBody UpdateNicknameRequest updateNicknameRequest, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateNickname(updateNicknameRequest, userId);
    }

    @Operation(summary = "회원 비밀번호 수정")
    @PostMapping("/{nickname}/security")
    public void updatePassword(UpdatePassworRequest passworDto, Authentication authentication) {
        userAuthService.updatePassword(passworDto);
    }


    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/{nickname}")
    public void withdrawUserAccount(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        deleteUserAccountFacade.withdrawUserAccount(userId);
    }

}
