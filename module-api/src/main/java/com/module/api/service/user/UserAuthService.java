package com.module.api.service.user;


import com.module.api.dto.request.user.UpdatePassworDto;
import com.module.api.entity.user.UserAuthEntity;
import com.module.api.repository.user.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public Long saveUserAuth(Long userId, String password) {
        UserAuthEntity userAuthEntity = UserAuthEntity.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .updatedDate(LocalDateTime.now())
                .build();

        return userAuthRepository.save(userAuthEntity).getUserAuthId();
    }


    // TODO : 수정 필요
    public String findPassword(Long userId) {
        return userAuthRepository.findByUserId(userId).orElseThrow().getPassword();
    }



    @Transactional
    public void updatePassword(UpdatePassworDto passworDto) {
        UserAuthEntity userAuthEntity = UserAuthEntity.builder()
                .password(passwordEncoder.encode(passworDto.getPassword()))
                .updatedDate(LocalDateTime.now())
                .build();
        userAuthRepository.save(userAuthEntity);
    }


    public void withdrawUserAuth(Long userId) {
        userAuthRepository.deleteById(userId);
    }
}
