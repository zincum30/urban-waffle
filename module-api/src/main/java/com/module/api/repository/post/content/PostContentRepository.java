package com.module.api.repository.post.content;


import com.module.api.entity.post.PostContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostContentRepository extends JpaRepository<PostContentEntity, Long>, PostContentQueryRepository {

    Optional<PostContentEntity> findByPostId(Long postId);

    PostContentEntity getByPostId(Long postId);
}
