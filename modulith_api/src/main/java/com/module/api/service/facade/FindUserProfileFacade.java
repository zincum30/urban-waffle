package com.module.api.service.facade;

import com.module.api.dto.response.UserProfileResponse;
import com.module.api.service.user.UserDetailService;
import com.module.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserProfileFacade {

    private final UserService userService;
    private final UserDetailService userDetailService;

    public UserProfileResponse userProfileResponse(String nickname) {
        Long userId = userService.findUserIdByNickname(nickname);
        return UserProfileResponse.builder()
                .nickname(nickname)
                .profileImagePath(userService.findProfileImgPath(userId))
                .email(userDetailService.findEmailByUserId(userId))
                .build();
    }

}
