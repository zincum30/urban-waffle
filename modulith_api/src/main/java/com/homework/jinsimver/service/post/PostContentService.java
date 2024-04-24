package com.homework.jinsimver.service.post;

import com.homework.jinsimver.dto.request.CreatePostContentDto;
import com.homework.jinsimver.dto.request.UpdatePostContentDto;
import com.homework.jinsimver.dto.response.FetchPostContentResponse;
import com.homework.jinsimver.entity.post.PostContentEntity;
import com.homework.jinsimver.repository.post.content.PostContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;

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
