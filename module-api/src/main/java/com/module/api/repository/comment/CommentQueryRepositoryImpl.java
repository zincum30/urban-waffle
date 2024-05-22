package com.module.api.repository.comment;

import com.module.api.dto.response.CommentsResponse;
import com.module.api.entity.comment.QCommentEntity;
import com.module.api.entity.post.QPostMetaEntity;
import com.module.api.entity.user.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;


@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QCommentEntity commentEntity = QCommentEntity.commentEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QPostMetaEntity postMetaEntity = QPostMetaEntity.postMetaEntity;

    public List<CommentsResponse> fetchCommentList(Long postId, Pageable pageable) {
        return jpaQueryFactory.select(Projections.fields(CommentsResponse.class,
                        userEntity.nickname,
                        commentEntity.detail,
                        commentEntity.createdDate,
                        commentEntity.modifiedDate))
                .from(commentEntity)
                .join(userEntity).on(commentEntity.userId.eq(userEntity.userId))
                .where(commentEntity.postId.eq(postId),
                        commentEntity.parentId.eq(0L))
                .offset(0)
                .limit(10)
                .fetch();
    }





}
