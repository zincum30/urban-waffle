package com.module.api.service.user;


import com.module.api.dto.request.user.CreateUserRequest;
import com.module.api.dto.request.user.ProfileImageRequest;
import com.module.api.dto.request.user.UpdateNicknameRequest;
import com.module.api.entity.user.UserEntity;
import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import com.module.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void checkDuplicatedNickname(String nickname) {
        boolean isDupicatedNickname = userRepository.existsByNickname(nickname);
        if(isDupicatedNickname) {
            throw new ApiException(ApiErrorCode.CONFLICT_NICKNAME);
        }
    }

    @Transactional
    public Long saveUserNickname(CreateUserRequest createUserRequest) {
        String email = createUserRequest.getEmail();
        UserEntity userEntity = UserEntity.builder()
                .nickname(createUserRequest.getNickname())
                .build();
        return userRepository.save(userEntity).getUserId();
    }

//    private String nicknameGenerator(String email) {
//        CreateUserRequest createUserDto = new CreateUserRequest();
//        if (createUserDto.getNickname().isEmpty())
//        return email.substring(0, email.indexOf("@"));
//    }


    public Boolean isDormant(Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        return userEntity.getLastLoginDate() == LocalDateTime.now().minusMonths(1);
    }


    public void updateLastLoginDate(Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        userEntity.updateLoginDate(LocalDateTime.now().minusMonths(1L));
        userRepository.save(userEntity);
    }



    @Transactional
    public void updateUserImg(ProfileImageRequest profileImageRequest, Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        userEntity.updateImageUrl(profileImageRequest.getImgUrl());
        userRepository.save(userEntity);
    }

    @Transactional
    public void updateNickname(UpdateNicknameRequest updateNicknameRequest, Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        userEntity.updateNickname(updateNicknameRequest.getNickname());
        userRepository.save(userEntity);
    }


    public Long findUserIdByNickname(String nickname) {
        UserEntity userEntity = userRepository.findByNickname(nickname).orElseThrow(
                () -> new ApiException(ApiErrorCode.USER_NOT_FOUND));
        return userEntity.getUserId();
    }

    public String findUserImgPath(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        return userEntity.getProfileImagePath();
    }

    public void withdrawUser(Long userId) {
        userRepository.deleteById(userId);
    }


}

