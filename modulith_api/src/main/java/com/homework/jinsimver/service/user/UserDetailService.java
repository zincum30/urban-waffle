package com.homework.jinsimver.service.user;

import com.homework.jinsimver.dto.request.UserDetailDto;
import com.homework.jinsimver.entity.user.UserDetailEntity;
import com.homework.jinsimver.exception.CustomErrorCode;
import com.homework.jinsimver.exception.CustomException;
import com.homework.jinsimver.repository.user.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
        if (userDetailRepository.findByEmail(email) != null) {
            throw new CustomException(CustomErrorCode.CONFLICT_EMAIL);
        }
    }



}
