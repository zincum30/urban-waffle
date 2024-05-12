package com.module.api.repository.post.meta;

import com.module.api.dto.response.FetchPostListResponse;
import com.module.api.entity.post.QPostImageEntity;
import com.module.api.entity.post.QPostMetaEntity;
import com.module.api.entity.user.QUserEntity;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class PostMetaQueryRepositoryImpl implements PostMetaQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QPostImageEntity postImageEntity = QPostImageEntity.postImageEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QPostMetaEntity postMetaEntity = QPostMetaEntity.postMetaEntity;


    @Override
    public List<FetchPostListResponse> fetchPostList(Pageable pageable) {
        return jpaQueryFactory.select(Projections.fields(FetchPostListResponse.class,
                        userEntity.nickname,
                        postMetaEntity.createdDate,
                        postMetaEntity.modifiedDate,
                        ExpressionUtils.as(
                                JPAExpressions.select(postImageEntity.postImgPath)
                                        .from(postImageEntity)
                                        .where(postImageEntity.postId.eq(postMetaEntity.postId))
                                        .limit(1), "thumnailPath")))
                .from(postMetaEntity)
                .join(userEntity)
                .on(userEntity.userId.eq(postMetaEntity.userId))
                .orderBy(postMetaEntity.postId.desc())
                .limit(10)
                .offset(0)
                .fetch();

    }

}
