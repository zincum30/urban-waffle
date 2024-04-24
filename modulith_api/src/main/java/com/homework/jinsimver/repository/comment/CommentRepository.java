package com.homework.jinsimver.repository.comment;

import com.homework.jinsimver.entity.comment.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>, CommentQueryRepository {

    CommentEntity getByPostId(Long postId);


}
