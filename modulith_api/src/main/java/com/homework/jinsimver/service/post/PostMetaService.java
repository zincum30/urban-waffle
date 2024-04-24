package com.homework.jinsimver.service.post;

import com.homework.jinsimver.dto.response.FetchPostListResponse;
import com.homework.jinsimver.entity.post.PostMetaEntity;
import com.homework.jinsimver.exception.CustomErrorCode;
import com.homework.jinsimver.exception.CustomException;
import com.homework.jinsimver.repository.post.meta.PostMetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class PostMetaService {

    private final PostMetaRepository postMetaRepository;


    // 포스트 작성 버튼 클릭 시
    @Transactional
    public Long createMetaPost(Long userId) {
        PostMetaEntity postMetaEntity = PostMetaEntity.builder()
                .userId(userId)
                .build();
        postMetaRepository.save(postMetaEntity);
        return postMetaEntity.getPostId();
    }



    public FetchPostListResponse fetchPostsList(Pageable pageable) {return fetchPostsList(PageRequest.of(10, 10));}




    // CASCADE 설정 확인 완료. 잘됨.
    @Transactional
    public void deletePost(Long postId, Long userId) {
        PostMetaEntity postMetaEntity = postMetaRepository.getById(postId);
        if (postMetaEntity.getUserId() == userId) {
            postMetaRepository.delete(postMetaEntity);
        } else throw new CustomException(CustomErrorCode.BAD_REQUEST);
    }

}
