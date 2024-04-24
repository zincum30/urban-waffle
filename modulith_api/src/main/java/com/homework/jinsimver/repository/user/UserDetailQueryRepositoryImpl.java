package com.homework.jinsimver.repository.user;

import com.homework.jinsimver.dormant.entity.InactiveUserDetailEntity;
import com.homework.jinsimver.dto.request.InactiveUserDto;
import com.homework.jinsimver.entity.user.QUserDetailEntity;
import com.homework.jinsimver.entity.user.QUserEntity;
import com.homework.jinsimver.entity.user.UserDetailEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Slice<UserDetailEntity> findInactiveUsers(Integer cursorId) {

        List<UserDetailEntity> inactiveUserDetail = jpaQueryFactory
                .selectFrom(userDetailEntity)
                .innerJoin(userEntity)
                .on(userDetailEntity.userId.eq(userEntity.userId))
                .where(userEntity.lastLoginDate.before(LocalDateTime.now().minusMonths(1L)))
//                        userDetailEntity.userDetailId.eq(cursorId.longValue() + 1))
                .limit(DEFAULT_SIZE)
                .fetch();

//        List<InactiveUserDto> inactiveUsers = inactiveUserDetail
//                .stream()
//                .map(x -> new InactiveUserDto(x))
//                .collect(Collectors.toList());
//
//        boolean hasNext = false;
//        if(inactiveUserDetail.size() > DEFAULT_SIZE) {
//            inactiveUsers.remove(DEFAULT_SIZE);
//            hasNext = true;
//        }

        return new SliceImpl<>(inactiveUserDetail, PageRequest.of(cursorId, DEFAULT_SIZE), false);
    }

//
//    private BooleanExpression cursor(Integer cursorId) {
//        return cursorId == null ? userDetailEntity.userDetailId.lt(cursorId + 1) : null;
//        }


    }


