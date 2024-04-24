package com.homework.jinsimver.repository.post.meta;

import com.homework.jinsimver.dto.response.FetchPostListResponse;
import com.homework.jinsimver.repository.post.content.PostContentQueryRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostMetaQueryRepository {

    List<FetchPostListResponse> fetchPostList(Pageable pageable);
}
