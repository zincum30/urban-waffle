package com.module.api.service.facade;


import com.module.api.dto.request.post.UpdatePostContentDto;
import com.module.api.service.post.PostContentService;
import com.module.api.service.post.PostMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostContentFacade {

    private final PostMetaService postMetaService;
    private final PostContentService postContentService;

    public void updatePostContent(Long postId, Long userId, UpdatePostContentDto updatePostContentDto) {
        if (postMetaService.findUserIdByPostId(postId).equals(userId)) {
            postContentService.updatePostContent(postId, updatePostContentDto.getContent());

        }
    }
}
