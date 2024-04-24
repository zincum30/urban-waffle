package com.homework.jinsimver.service.user;


import com.homework.jinsimver.dto.request.ProfileImageRequest;
import com.homework.jinsimver.dto.request.UpdateNicknameDto;
import com.homework.jinsimver.entity.user.UserEntity;
import com.homework.jinsimver.exception.CustomErrorCode;
import com.homework.jinsimver.exception.CustomException;
import com.homework.jinsimver.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

