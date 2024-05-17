package com.module.api.repository.comment;

import com.module.api.dto.response.CommentsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CommentQueryRepository {

    List<CommentsResponse> fetchCommentList(Long postId, Pageable pageable);

}
