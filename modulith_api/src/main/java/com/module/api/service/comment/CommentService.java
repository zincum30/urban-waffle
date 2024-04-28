package com.module.api.service.comment;

import com.module.api.dto.request.CreateCommentDto;
import com.module.api.dto.response.FetchCommentListResponse;
import com.module.api.entity.comment.CommentEntity;
import com.module.api.exception.CustomErrorCode;
import com.module.api.exception.CustomException;
import com.module.api.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    // TODO : 뭐가 문제냙!!!!!!!!!!!

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
                .build();
        commentRepository.save(commentEntity);
    }

    public void addReply(Long postId, Long commentId, Long userId, CreateCommentDto createCommentDto) {
        CommentEntity commentEntity = CommentEntity.builder()
                .postId(postId)
                .parentId(commentId)
                .userId(userId)
                .detail(createCommentDto.getDetail())
                .delete(false)
                .build();
        commentRepository.save(commentEntity);
    }

    public void updateComment(Long commentId, Long userId, String detail) {
        CommentEntity commentEntity = commentRepository.getById(commentId);
        if (commentEntity.getUserId().equals(userId)) {
            commentEntity.updateDetail(detail);
        } else throw new CustomException(CustomErrorCode.NOT_AUTHORIZED);

        commentEntity.builder()
                .detail(detail)
                .build();
        commentRepository.save(commentEntity);

    }


    public void deleteComment(Long commentId, Long userId) {
        CommentEntity commentEntity = commentRepository.getById(commentId);
        if (!commentEntity.getUserId().equals(userId)) {
            throw new CustomException(CustomErrorCode.NOT_AUTHORIZED);
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
