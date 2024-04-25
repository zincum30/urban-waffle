package com.module.api.repository.comment;

import com.module.api.dto.response.FetchCommentListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CommentQueryRepository {

    List<FetchCommentListResponse> fetchCommentList(Long postId, Pageable pageable);

}
