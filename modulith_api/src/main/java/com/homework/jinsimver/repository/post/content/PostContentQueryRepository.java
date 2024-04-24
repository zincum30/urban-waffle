package com.homework.jinsimver.repository.post.content;

import com.homework.jinsimver.dto.response.FetchPostContentResponse;
import com.homework.jinsimver.entity.post.PostContentEntity;

import java.util.List;

public interface PostContentQueryRepository {

    FetchPostContentResponse fetchContent(Long postId);

}
