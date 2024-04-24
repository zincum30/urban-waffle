package com.homework.jinsimver.repository.comment;

import com.homework.jinsimver.dto.response.FetchCommentListResponse;
import com.homework.jinsimver.entity.comment.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentQueryRepository {

    List<FetchCommentListResponse> fetchCommentList(Long postId, Pageable pageable);

}
