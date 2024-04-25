package com.module.api.service.user;


import com.module.api.dto.request.ProfileImageRequest;
import com.module.api.dto.request.UpdateNicknameDto;
import com.module.api.entity.user.UserEntity;
import com.module.api.exception.CustomErrorCode;
import com.module.api.exception.CustomException;
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
        if (userRepository.findByNickname(nickname).isEmpty()) {
            throw new CustomException(CustomErrorCode.CONFLICT_NICKNAME);
        }
    }

    @Transactional
    public Long saveUser(String nickname) {
        UserEntity userEntity = UserEntity.builder()
                .nickname(nickname)
                .build();
        return userRepository.save(userEntity).getUserId();
    }

    @Transactional
    public void updateLastLoginDate(Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        userEntity.updateLoginDate(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    public void logout() {
    }


    @Transactional
    public void updateUserImg(ProfileImageRequest profileImageRequest, Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        userEntity.updateImageUrl(profileImageRequest.getImgUrl());
        userRepository.save(userEntity);
    }

    @Transactional
    public void updateNickname(UpdateNicknameDto nicknameDto, Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        userEntity.updateNickname(nicknameDto.getNickname());
        userRepository.save(userEntity);
    }


//    public List<UserEntity> getInactiveUserList() {
//        List<UserEntity> inactiveUserEntities = userRepository.findInactiveUsers(null);
//        return inactiveUserEntities.stream()
//                .map(map -> new UserEntity())
//                .collect(Collectors.toList());
//    }


    public Long findUserIdByNickname(String nickname) {
        UserEntity userEntity = userRepository.findByNickname(nickname).orElseThrow();
        return userEntity.getUserId();
    }


}

