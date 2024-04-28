package com.module.api.repository.post.content;

import com.module.api.dto.response.FetchPostContentResponse;

import com.module.api.entity.post.QPostContentEntity;
import com.module.api.entity.post.QPostMetaEntity;
import com.module.api.entity.user.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostContentQueryRepositoryImpl implements PostContentQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private static final QPostContentEntity postContentEntity = QPostContentEntity.postContentEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QPostMetaEntity postMetaEntity = QPostMetaEntity.postMetaEntity;


    @Override
    public FetchPostContentResponse fetchContent(Long postId) {
        return jpaQueryFactory.select(Projections.fields(FetchPostContentResponse.class,
                userEntity.nickname,
                postContentEntity.content,
                postMetaEntity.createdDate,
                postMetaEntity.modifiedDate))
                .from(postMetaEntity)
                .innerJoin(userEntity).on(postMetaEntity.userId.eq(userEntity.userId))
                .join(postContentEntity).on(postMetaEntity.postId.eq(postContentEntity.postId))
                .orderBy(postMetaEntity.createdDate.asc())
                .fetchOne();

    }


}
