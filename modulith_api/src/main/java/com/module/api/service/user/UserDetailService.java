package com.module.api.service.user;

import com.module.api.dto.response.UserProfileResponse;
import com.module.api.entity.user.UserDetailEntity;
import com.module.api.exception.CustomErrorCode;
import com.module.api.exception.CustomException;
import com.module.api.repository.user.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.font.LayoutPath;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailService {

    private final UserDetailRepository userDetailRepository;


    @Transactional
    public Long saveUserDetail(Long userId, String name, String email, String phoneNumber) {
        UserDetailEntity userDetailEntity = UserDetailEntity.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .joinedDate(LocalDateTime.now())
                .build();
        return userDetailRepository.save(userDetailEntity).getUserDetailId();
    }


    // TODO : 재확인 필요
    public Long findUserIdByEmail(String email) {
        return userDetailRepository.findUserIdByEmail(email).orElseThrow().getUserId();
    }


    // TODO : email에 해당하는 정보가 없는 경우 exception이 나는지? null이 return 되는지? 확인이 필요하겠구뇽~
    public void checkDuplicatedEmail(String email) {
        if (userDetailRepository.findByEmail(email).isEmpty()) {
            throw new CustomException(CustomErrorCode.CONFLICT_EMAIL);
        }
    }

    public boolean existsByUserId(Long userId) {
        return userDetailRepository.existsById(userId);
    }

    public void withdrawUserDetail(Long userId) {
        userDetailRepository.deleteById(userId);
    }

    public String findEmailByUserId(Long userId) {
        UserDetailEntity userDetailEntity = userDetailRepository.findByUserId(userId).orElseThrow();
        return userDetailEntity.getEmail();
    }


}
