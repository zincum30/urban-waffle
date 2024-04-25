package com.module.api.repository.comment;

import com.module.api.dto.response.FetchCommentListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentQueryRepository {

    List<FetchCommentListResponse> fetchCommentList(Long postId, Pageable pageable);

}
