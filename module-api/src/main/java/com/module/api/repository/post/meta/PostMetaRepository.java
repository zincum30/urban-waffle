package com.module.api.repository.post.meta;

import com.module.api.entity.post.PostMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMetaRepository extends JpaRepository<PostMetaEntity, Long>, PostMetaQueryRepository {

    PostMetaEntity getById(Long postId);


}
