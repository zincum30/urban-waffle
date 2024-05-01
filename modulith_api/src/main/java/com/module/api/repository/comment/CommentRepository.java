package com.module.api.repository.comment;

import com.module.api.entity.comment.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>, CommentQueryRepository {

    Optional<CommentEntity> findByPostId(Long postId);


}
