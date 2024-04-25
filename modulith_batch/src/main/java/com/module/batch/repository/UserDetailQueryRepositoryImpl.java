package com.module.batch.repository;


import com.module.batch.dto.InactiveUserDto;

import com.module.batch.entity.QUserDetailEntity;
import com.module.batch.entity.QUserEntity;
import com.module.batch.entity.UserDetailEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class UserDetailQueryRepositoryImpl implements UserDetailQueryRepository {


    private final JPAQueryFactory jpaQueryFactory;
    private final QUserEntity userEntity = QUserEntity.userEntity;
    private final QUserDetailEntity userDetailEntity = QUserDetailEntity.userDetailEntity;

    private static final int DEFAULT_SIZE = 10;


    @Override
    public Slice<InactiveUserDto> findInactiveUsers(Long cursorId) {

        List<UserDetailEntity> inactiveUserDetail = jpaQueryFactory
                .selectFrom(userDetailEntity)
                .innerJoin(userEntity)
                .on(userDetailEntity.userId.eq(userEntity.userId))
                .where(userEntity.lastLoginDate.before(LocalDateTime.now().minusMonths(1L)),
                        userDetailEntity.userDetailId.gt(cursorId))
                .limit(DEFAULT_SIZE + 1)
                .fetch();

        List<InactiveUserDto> inactiveUsers = inactiveUserDetail
                .stream()
                .map(x -> new InactiveUserDto(x))
                .collect(Collectors.toList());

        boolean hasNext = false;
        if (inactiveUserDetail.size() > DEFAULT_SIZE) {
            inactiveUsers.remove(DEFAULT_SIZE);
            hasNext = true;
        }

        return new SliceImpl<>(inactiveUsers, PageRequest.of(0, DEFAULT_SIZE), hasNext);
    }

//
//    private BooleanExpression cursor(Integer cursorId) {
//        return cursorId == null ? userDetailEntity.userDetailId.lt(cursorId + 1) : null;
//        }


}


