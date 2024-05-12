package com.module.api.service.post;

import com.module.api.dto.request.CreatePostContentDto;
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


    // TODO : update 일단 보류,, 생성/수정일자가 meta에 들어가는 게 맞지 않나???
//    public void updatePostContent(UpdatePostContentDto updatePostContentDto) {
//
//        PostContentEntity postContentEntity = postContentRepository.getById(postContentId);
//        postContentEntity = PostContentEntity.builder()
//                .
//                .content(updatePostContentDto.getContent())
//                .build();
//        postContentRepository.save(postContentEntity);
//
//    }
}
