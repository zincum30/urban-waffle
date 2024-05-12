package com.module.batch.job.bean;


import com.module.batch.dto.InactiveUserDto;
import com.module.batch.dto.MultiDataSourceDto;
import com.module.batch.entity.InactiveUserDetailEntity;
import com.module.batch.entity.UserDetailEntity;
import com.module.batch.repository.InactiveUserDetailRepository;
import com.module.batch.repository.UserDetailRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@StepScope
@Transactional
@RequiredArgsConstructor
public class MultiDataSourceBean implements ItemProcessor<InactiveUserDto, MultiDataSourceDto>, ItemWriter<MultiDataSourceDto> {

    private final UserDetailRepository mainRepository;
    private final InactiveUserDetailRepository dormantRepository;

    private final DataAccess dataAccess;
    private final JdbcTemplate jdbcTemplate;


    @Nullable
    public MultiDataSourceDto process(@NotNull InactiveUserDto inactiveUserDto) {
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

        dataAccess.writeLock.lock();
        try {
            items.forEach(item -> {
                dormantRepository.save(item.getDormant());
                mainRepository.deleteById(item.getMain().getUserDetailId());
            });

        } finally {
            dataAccess.writeLock.unlock();
        }
    }

}
