package com.module.api.service.comment;

import com.module.api.dto.request.CreateCommentDto;
import com.module.api.dto.response.FetchCommentListResponse;
import com.module.api.entity.comment.CommentEntity;
import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import com.module.api.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    public List<FetchCommentListResponse> fetchCommentList(Long postId, Pageable pageable) {
        return commentRepository.fetchCommentList(postId, pageable);
    }

    public void addComment(Long postId, Long userId, String detail) {

        CommentEntity commentEntity = CommentEntity.builder()
                .postId(postId)
                .userId(userId)
                .parentId(0L)
                .detail(detail)
                .delete(false)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        commentRepository.save(commentEntity);
    }

    public void addReply(Long commentId, Long userId, CreateCommentDto createCommentDto) {
        CommentEntity commentEntity = CommentEntity.builder()
                .parentId(commentId)
                .userId(userId)
                .detail(createCommentDto.getDetail())
                .delete(false)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        commentRepository.save(commentEntity);
    }

    public void updateComment(Long commentId, Long userId, String detail) {
        CommentEntity commentEntity = commentRepository.getById(commentId);
        if (commentEntity.getUserId().equals(userId)) {
            commentEntity.updateDetail(detail);
        } else throw new ApiException(ApiErrorCode.NOT_AUTHORIZED);

        commentEntity = CommentEntity.builder()
                .commentId(commentId)
                .detail(detail)
                .userId(userId)
                .parentId(commentEntity.getParentId())
                .createdDate(commentEntity.getCreatedDate())
                .modifiedDate(LocalDateTime.now())
                .build();
        commentRepository.save(commentEntity);

    }


    public void deleteComment(Long commentId, Long userId) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow();

        if (!commentEntity.getUserId().equals(userId)) {
            throw new ApiException(ApiErrorCode.NOT_AUTHORIZED);
        }

        commentEntity = CommentEntity.builder()
                .commentId(commentId)
                .postId(commentEntity.getPostId())
                .parentId(commentEntity.getParentId())
                .userId(userId)
                .detail("삭제된 댓글입니다.")
                .delete(true)
                .build();

        commentRepository.save(commentEntity);
    }
}
