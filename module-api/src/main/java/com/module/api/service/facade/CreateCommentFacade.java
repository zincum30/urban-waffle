package com.module.api.service.facade;

import com.module.api.dto.request.PostCommentRequest;
import com.module.api.service.comment.CommentService;
import com.module.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentFacade {

    private final UserService userService;
    private final CommentService commentService;

    public void createComment(Long postId, PostCommentRequest postCommentRequest) {
        Long userId = userService.findUserIdByNickname(postCommentRequest.getNickname());
        commentService.addComment(postId, userId, postCommentRequest.getDetail());
    }
}
