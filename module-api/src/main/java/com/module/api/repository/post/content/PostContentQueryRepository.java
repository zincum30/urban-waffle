package com.module.api.repository.post.content;

import com.module.api.dto.response.FetchPostContentResponse;

public interface PostContentQueryRepository {

    FetchPostContentResponse fetchContent(Long postId);

}
