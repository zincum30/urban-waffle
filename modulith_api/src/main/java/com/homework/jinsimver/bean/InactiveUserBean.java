//package com.homework.jinsimver.bean;
//
//
//import com.homework.jinsimver.dto.request.InactiveUserDto;
//import com.homework.jinsimver.entity.user.UserDetailEntity;
//import com.homework.jinsimver.repository.user.UserDetailRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@StepScope
//@RequiredArgsConstructor
//public class InactiveUserBean implements ItemReader<InactiveUserDto> {
//
//
//    // TO DO : DTO 적용하기..
//    private final UserDetailRepository userDetailRepository;
//
//    // private int targetIndex = 10;
//    private Integer currentIndex = 0;
//
//    @Override
//    public InactiveUserDto read() {
//
//        InactiveUserDto inactiveUsers;
//        inactiveUsers = userDetailRepository
//                .findInactiveUsers(currentIndex);
//
//
//        return inactiveUsers;
//
//    }
//
//
//}
