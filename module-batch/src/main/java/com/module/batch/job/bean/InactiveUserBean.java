package com.module.batch.job.bean;


import com.module.batch.dto.InactiveUserDto;
import com.module.batch.repository.UserDetailRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@StepScope
@Transactional
@RequiredArgsConstructor
public class InactiveUserBean implements ItemReader<InactiveUserDto> {


    private final DataAccess dataAccess;
    private final UserDetailRepository userDetailRepository;

    private Slice<InactiveUserDto> inactiveUserDtos = null;

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

        dataAccess.readLock.lock();

        try {
            if (currentIndex < inactiveUserDtos.getSize()) {
                InactiveUserDto dto = inactiveUserDtos.getContent().get(currentIndex++);
                cursorId = dto.getInactiveUser().getUserDetailId();
                return dto;
            } else {
                return null;
            }
        } finally {
            dataAccess.readLock.unlock();
        }

    }
}
