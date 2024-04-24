package com.homework.jinsimver.repository.post.image;

import com.homework.jinsimver.entity.post.PostImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepository extends JpaRepository<PostImageEntity, Long> {

    PostImageEntity getByPostId(Long postId);

}
