package com.module.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {

    String profileImagePath;
    String nickname;
    String email;

}
