package com.homework.jinsimver.repository.post.meta;

import com.homework.jinsimver.entity.post.PostMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMetaRepository extends JpaRepository<PostMetaEntity, Long>, PostMetaQueryRepository {

    PostMetaEntity getById(Long postId);


}
