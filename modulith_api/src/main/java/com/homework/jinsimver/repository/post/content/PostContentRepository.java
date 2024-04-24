package com.homework.jinsimver.repository.post.content;


import com.homework.jinsimver.entity.post.PostContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostContentRepository extends JpaRepository<PostContentEntity, Long>, PostContentQueryRepository {

    Optional<PostContentEntity> findByPostId(Long postId);
}
