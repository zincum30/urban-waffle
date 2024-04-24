package com.homework.jinsimver.controller;

import com.homework.jinsimver.dto.request.LoginDto;
import com.homework.jinsimver.dto.request.CreateUserDto;
import com.homework.jinsimver.dto.request.UpdateNicknameDto;
import com.homework.jinsimver.dto.request.ProfileImageRequest;
import com.homework.jinsimver.dto.request.UpdatePassworDto;
import com.homework.jinsimver.dto.response.LoginResponse;
import com.homework.jinsimver.service.user.UserAuthService;
import com.homework.jinsimver.service.user.UserDetailService;
import com.homework.jinsimver.service.user.UserService;
import com.homework.jinsimver.service.facade.LoginFacade;
import com.homework.jinsimver.service.facade.RegisterFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody CreateUserDto createUserDto) {
        registerFacade.register(createUserDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/check-email")
    public ResponseEntity<HttpStatus> checkDuplicatedEmail(@RequestParam String email) {
        userDetailService.checkDuplicatedEmail(email);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<HttpStatus> checkDuplicatedNickname(@RequestParam String nickname) {
        userService.checkDuplicatedNickname(nickname);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto loginDto) {
        return loginFacade.signIn(loginDto);
    }



    @PutMapping("/image")
    public ResponseEntity<HttpStatus> updateProfileImg(
            @RequestBody ProfileImageRequest profileImageRequest, Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateUserImg(profileImageRequest, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/nickname") // TODO : update-nickname이 나을지, update-nickname이 나을지--RESTful
    public ResponseEntity<HttpStatus> updateNickname(UpdateNicknameDto nickname, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateNickname(nickname, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/password") // TODO : 이러면 어떤 유저의 패스워드 수정인지 어케 앎????
    public ResponseEntity<HttpStatus> updatePassword(UpdatePassworDto passworDto) {
        userAuthService.updatePassword(passworDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/profile")
    public void findUserProfile(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
    }



}
