package com.module.api.repository.comment;

import com.module.api.entity.comment.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>, CommentQueryRepository {

    CommentEntity getByPostId(Long postId);


}
