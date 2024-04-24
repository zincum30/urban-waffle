package com.homework.jinsimver.repository.post.content;

import com.homework.jinsimver.dto.response.FetchPostContentResponse;
import com.homework.jinsimver.entity.post.PostContentEntity;
import com.homework.jinsimver.entity.post.QPostContentEntity;
import com.homework.jinsimver.entity.post.QPostMetaEntity;
import com.homework.jinsimver.entity.user.QUserEntity;
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
                postContentEntity.createdDate,
                postContentEntity.modifiedDate))
                .from(postMetaEntity)
                .innerJoin(userEntity).on(postMetaEntity.userId.eq(userEntity.userId))
                .join(postContentEntity).on(postMetaEntity.postId.eq(postContentEntity.postId))
                .orderBy(postContentEntity.createdDate.asc())
                .fetchOne();

    }


}
