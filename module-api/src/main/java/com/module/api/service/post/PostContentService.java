package com.module.api.service.post;

import com.module.api.dto.request.post.CreatePostContentDto;
import com.module.api.dto.response.FetchPostContentResponse;
import com.module.api.entity.post.PostContentEntity;
import com.module.api.repository.post.content.PostContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostContentService {

    private final PostContentRepository postContentRepository;

    public void savePostContent(Long postId, CreatePostContentDto contentDto) {
        PostContentEntity contentEntity = PostContentEntity.builder()
                .postId(postId)
                .content(contentDto.getContent())
                .build();
        postContentRepository.save(contentEntity);
    }


    public FetchPostContentResponse fetchPostContent(Long postId) {

        return postContentRepository.fetchContent(postId);
    }


    public void updatePostContent(Long postId, String content) {
        PostContentEntity postContentEntity = postContentRepository.getByPostId(postId);

        postContentEntity = PostContentEntity.builder()
                .postId(postId)
                .postContentId(postContentEntity.getPostContentId())
                .content(content)
                .build();
        postContentRepository.save(postContentEntity);
    }
}
