package com.homework.jinsimver.repository.post.meta;

import com.homework.jinsimver.dto.response.FetchPostListResponse;
import com.homework.jinsimver.entity.post.QPostImageEntity;
import com.homework.jinsimver.entity.post.QPostMetaEntity;
import com.homework.jinsimver.entity.user.QUserEntity;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;

@RequiredArgsConstructor
public class PostMetaQueryRepositoryImpl implements PostMetaQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QPostImageEntity postImageEntity = QPostImageEntity.postImageEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QPostMetaEntity postMetaEntity = QPostMetaEntity.postMetaEntity;


    @Override
    public List<FetchPostListResponse> fetchPostList(Pageable pageable) {
        return jpaQueryFactory.selectFrom(postMetaEntity)
                .join(userEntity).on(postMetaEntity.userId.eq(userEntity.userId))
                .leftJoin(postImageEntity).on(postMetaEntity.postId.eq(postImageEntity.imageId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(groupBy(postMetaEntity.postId).list(Projections.constructor(FetchPostListResponse.class,
                                userEntity.nickname, postImageEntity.imgUrl,
                                ExpressionUtils.as(
                                        JPAExpressions.select(postImageEntity.imgUrl)
                                                .from(postImageEntity)
                                                .where(postImageEntity.postId.eq(postMetaEntity.postId))
                                                .limit(1)
                                                .orderBy(postImageEntity.imageId.asc()), "thumnail"
                                )
                        )
                ));
    }

}
