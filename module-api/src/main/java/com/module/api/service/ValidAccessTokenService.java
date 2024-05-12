package com.module.api.service;

import com.module.api.authentication.jwt.JwtProvider;
import com.module.api.entity.user.UserEntity;
import com.module.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ValidAccessTokenService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public void findUserByToken(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        UserEntity userEntity = userRepository.getById(userId);


    }
}
