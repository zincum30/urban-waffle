package com.module.api.service.user;

import com.module.api.entity.user.UserDetailEntity;
import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import com.module.api.repository.user.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .joinedDate(LocalDateTime.now())
                .build();
        return userDetailRepository.save(userDetailEntity).getUserDetailId();
    }


    // TODO : 재확인 필요
    public Long findUserIdByEmail(String email) {
        return userDetailRepository.findUserIdByEmail(email).orElseThrow().getUserId();
    }


    public void checkDuplicatedEmail(String email) {
        if (userDetailRepository.findByEmail(email).isPresent()) {
            throw new ApiException(ApiErrorCode.CONFLICT_EMAIL);
        }
    }

    private String nicknameGenerator(String email) {
        return email.substring(0, email.indexOf("@"));
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

    public boolean matchUserDetail(String name, String email) {
        UserDetailEntity userDetail = userDetailRepository.findUserIdByEmail(email).orElseThrow();
        return userDetail.getName().equals(name);

    }

}
