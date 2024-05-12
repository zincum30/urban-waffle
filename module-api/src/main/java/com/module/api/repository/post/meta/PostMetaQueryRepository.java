package com.module.api.repository.post.meta;

import com.module.api.dto.response.FetchPostListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostMetaQueryRepository {

    List<FetchPostListResponse> fetchPostList(Pageable pageable);
}
