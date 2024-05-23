package com.module.api.service.user;


import com.module.api.dto.request.user.UpdatePassworRequest;
import com.module.api.entity.user.UserAuthEntity;
import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import com.module.api.repository.user.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Transactional
    public Long saveUserAuth(Long userId, String password) {
        UserAuthEntity userAuthEntity = UserAuthEntity.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .updatedDate(LocalDateTime.now())
                .build();

        return userAuthRepository.save(userAuthEntity).getUserAuthId();
    }



    public String findPassword(Long userId) {
        UserAuthEntity userAuthEntity;
        if(userAuthRepository.existsByUserId(userId)) {
            userAuthEntity = userAuthRepository.getReferenceByUserId(userId);
        } else throw new ApiException(ApiErrorCode.USER_NOT_FOUND);
        return userAuthEntity.getPassword();
    }



    @Transactional
    public void updatePassword(UpdatePassworRequest passworDto) {
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
