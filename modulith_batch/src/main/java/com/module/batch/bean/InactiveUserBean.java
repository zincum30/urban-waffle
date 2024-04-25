package com.module.batch.bean;


import com.module.batch.dto.InactiveUserDto;
import com.module.batch.repository.UserDetailRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
public class InactiveUserBean implements ItemReader<InactiveUserDto> {


    // TO DO : DTO 적용하기..
    private final UserDetailRepository userDetailRepository;

    private Slice<InactiveUserDto> inactiveUserDtos = null;
    // private int targetIndex = 10;
    private Integer currentIndex = 0;
    private Long cursorId = -1L;

    @Override
    public InactiveUserDto read() {
        boolean needToReadNextPage = false;
        if (inactiveUserDtos == null) {
            needToReadNextPage = true;
        } else if (inactiveUserDtos.getSize() <= currentIndex && inactiveUserDtos.hasNext()) {
            needToReadNextPage = true;
        }

        if (needToReadNextPage) {
            inactiveUserDtos = userDetailRepository.findInactiveUsers(cursorId);
            currentIndex = 0;
        }

        if (currentIndex < inactiveUserDtos.getSize()) {
            InactiveUserDto dto = inactiveUserDtos.getContent().get(currentIndex++);
            cursorId = dto.getInactiveUser().getUserDetailId();
            return dto;
        } else {
            return null;
        }
    }
}
