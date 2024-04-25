package com.module.api.repository.comment;

import com.module.api.dto.response.FetchCommentListResponse;
import com.homework.jinsimver.entity.comment.QCommentEntity;
import com.homework.jinsimver.entity.post.QPostMetaEntity;
import com.homework.jinsimver.entity.user.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;


@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private static final QCommentEntity commentEntity = QCommentEntity.commentEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QPostMetaEntity postMetaEntity = QPostMetaEntity.postMetaEntity;

    public List<FetchCommentListResponse> fetchCommentList(Long postId, Pageable pageable) {
        return jpaQueryFactory.select(Projections.fields(FetchCommentListResponse.class,
                userEntity.nickname,
                commentEntity.detail,
                commentEntity.createdDate,
                commentEntity.modifiedDate))
                .from(commentEntity)
                .innerJoin(postMetaEntity).on(commentEntity.postId.eq(postMetaEntity.postId))
                .join(userEntity).on(commentEntity.userId.eq(userEntity.userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();



    }



}
