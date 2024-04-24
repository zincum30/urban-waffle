package com.homework.jinsimver.service.facade;

import com.homework.jinsimver.dto.request.PostCommentRequest;
import com.homework.jinsimver.service.comment.CommentService;
import com.homework.jinsimver.service.user.UserService;
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
