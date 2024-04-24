package com.homework.jinsimver.bean;

import com.homework.jinsimver.dormant.entity.InactiveUserDetailEntity;
import com.homework.jinsimver.dormant.repository.InactiveUserDetailRepository;
import com.homework.jinsimver.dto.request.InactiveUserDto;
import com.homework.jinsimver.dto.request.MultiDataSourceDto;
import com.homework.jinsimver.entity.user.UserDetailEntity;
import com.homework.jinsimver.repository.user.UserDetailRepository;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
public class MultiDataSourceBean implements ItemProcessor<InactiveUserDto, MultiDataSourceDto>, ItemWriter<MultiDataSourceDto> {

    private final UserDetailRepository mainRepository;
    private final InactiveUserDetailRepository dormantRepository;


    @Nullable
    public MultiDataSourceDto process (@NotNull InactiveUserDto inactiveUserDto) {
        UserDetailEntity userDetailEntity = inactiveUserDto.getInactiveUser();
        InactiveUserDetailEntity inactiveUserDetailEntity = InactiveUserDetailEntity.builder()
                .userDetailId(userDetailEntity.getUserDetailId())
                .userId(userDetailEntity.getUserId())
                .name(userDetailEntity.getName())
                .email(userDetailEntity.getEmail())
                .phoneNumber(userDetailEntity.getPhoneNumber())
                .joinedDate(userDetailEntity.getJoinedDate())
                .updatedDate(userDetailEntity.getUpdatedDate())
                .build();

        return new MultiDataSourceDto(userDetailEntity, inactiveUserDetailEntity);
    }


    @Override
    public void write(Chunk<? extends MultiDataSourceDto> items) {

        items.forEach(item -> {
            dormantRepository.save(item.getDormant());
            mainRepository.deleteById(item.getMain().getUserDetailId());
        });


    }

}
