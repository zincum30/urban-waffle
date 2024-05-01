package com.module.api.controller;

import com.module.api.dto.request.LoginDto;
import com.module.api.dto.request.CreateUserDto;
import com.module.api.dto.request.UpdateNicknameDto;
import com.module.api.dto.request.ProfileImageRequest;
import com.module.api.dto.request.UpdatePassworDto;
import com.module.api.dto.response.LoginResponse;
import com.module.api.service.facade.FindUserProfileFacade;
import com.module.api.service.facade.WithdrawUserAccountFacade;
import com.module.api.service.user.UserAuthService;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import com.module.api.service.facade.LoginFacade;
import com.module.api.service.facade.RegisterFacade;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final RegisterFacade registerFacade;
    private final LoginFacade loginFacade;
    private final UserDetailService userDetailService;
    private final UserService userService;
    private final UserAuthService userAuthService;
    private final WithdrawUserAccountFacade withdrawUserAccountFacade;
    private final FindUserProfileFacade findUserProfileFacade;

    @PostMapping
    public ResponseEntity<HttpStatus> register(@RequestBody CreateUserDto createUserDto) {
        registerFacade.register(createUserDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/mail")
    public ResponseEntity<String> checkDuplicatedEmail(@RequestParam("mail") String email) {
        userDetailService.checkDuplicatedEmail(email);
        return ResponseEntity.ok("AVAILABLE_MAIL_ADDRESS");
    }

    @GetMapping("/nickname")
    public ResponseEntity<String> checkDuplicatedNickname(@RequestParam("nickname") String nickname) {
        userService.checkDuplicatedNickname(nickname);
        return ResponseEntity.ok("AVAILABLE_NICKNAME");
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto loginDto) {
        return loginFacade.signIn(loginDto);
    }


    @PutMapping("/me/img")
    public ResponseEntity<HttpStatus> updateProfileImg(
            @RequestBody ProfileImageRequest profileImageRequest, Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateUserImg(profileImageRequest, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/me/nickname") // TODO : update-nickname이 나을지, nickname이 나을지?
    public ResponseEntity<HttpStatus> updateNickname(UpdateNicknameDto nickname, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateNickname(nickname, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/me/password") // TODO : 이러면 어떤 유저의 패스워드 수정인지 어케 앎????
    public ResponseEntity<HttpStatus> updatePassword(UpdatePassworDto passworDto) {
        userAuthService.updatePassword(passworDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/me")
    public void findUserProfile(String nickname) {

        findUserProfileFacade.userProfileResponse(nickname);

    }


    @DeleteMapping ("/withdraw")
    public ResponseEntity<HttpStatus> withdrawUserAccount(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        withdrawUserAccountFacade.withdrawUserAccount(userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
